package ru.rshbdigital.farmhub.client.offline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.rshbdigital.farmhub.client.counter.local.CounterLocalDataSource
import ru.rshbdigital.farmhub.client.counter.netrwork.CounterNetworkDataSource
import ru.rshbdigital.farmhub.client.offline.local.OfflineLocalDataSource
import ru.rshbdigital.farmhub.client.tasks.local.TasksLocalDataSource
import ru.rshbdigital.farmhub.client.tasks.network.TasksNetworkDataSource
import ru.rshbdigital.farmhub.core.api.converter.TaskConverter
import ru.rshbdigital.farmhub.core.mapper.toDomain
import ru.rshbdigital.farmhub.core.mapper.toEntity
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.util.OfflineLogger
import ru.rshbdigital.farmhub.core.util.becauseOfBadInternet
import ru.rshbdigital.farmhub.core.util.tryGetErrorCode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineRepository @Inject constructor(
    private val offlineLocalDataSource: OfflineLocalDataSource,
    private val counterNetworkDataSource: CounterNetworkDataSource,
    private val counterLocalDataSource: CounterLocalDataSource,
    private val tasksNetworkDataSource: TasksNetworkDataSource,
    private val tasksLocalDataSource: TasksLocalDataSource
) {

    companion object {
        const val PING_DELAY_MS = 10_000L
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _isOffline = MutableStateFlow(false)

    /**
     * Флоу для отображение инфы об офлайн режиме в UI
     */
    val isOffline = _isOffline.asStateFlow()

    init {
        scope.launch {
            while (true) {
                delay(PING_DELAY_MS)
                try {
                    val isOffline = !sendPing()
                    trySendAllRequests()
                    setIsOffline(isOffline)
                } catch (exception: Exception) {
                    setIsOffline(exception.becauseOfBadInternet())
                }
            }
        }
        scope.launch {
            isOffline.collectLatest { isOffline ->
                OfflineLogger.log("isOffline $isOffline")
                if (isOffline) {
                    trySendAllRequests()
                }
            }
        }
    }

    private val mutex = Mutex()

    fun setIsOffline(isOffline: Boolean) {
        _isOffline.value = isOffline
    }

    fun checkIsOffline(): Boolean = isOffline.value

    private suspend fun lockRequestsSending(sendRequests: suspend () -> Unit) = mutex.withLock {
        sendRequests()
    }

    private suspend fun sendPing(): Boolean {
        val success = try {
            tasksNetworkDataSource.getTasks(0)
            true
        } catch (exception: Exception) {
            !exception.becauseOfBadInternet()
        }
        return success
    }

    /**
     * Флоу для отображение списка запросов в отдельном
     * экране, который в теории может потом появиться
     */
    fun getAllRequestsFlow(): Flow<List<Request>> =
        offlineLocalDataSource.getAllRequestsFlow().map { requests ->
            requests.map { request ->
                request.toDomain()
            }
        }

    suspend fun trySendAllRequests() {
        lockRequestsSending {
            offlineLocalDataSource.getAllRequests().map { requests ->
                requests.toDomain()
            }.forEach { request ->
                try {
                    when (request) {
                        is Request.SetCounter -> {
                            val counter = counterNetworkDataSource.setCounter(request.count).toDomain()
                            counterLocalDataSource.saveCounter(counter.toEntity())
                        }
                        is Request.UpdateTask -> {
                            val task = tasksNetworkDataSource.updateTask(
                                request.taskId,
                                request.updateTaskRequest
                            )
                            val convertedTask = TaskConverter.fromNetwork(task)
                            if (convertedTask != null) {
                                tasksLocalDataSource.saveTask(
                                    TaskConverter.toEntity(
                                        convertedTask
                                    )
                                )
                            }
                        }
                    }
                    offlineLocalDataSource.deleteRequest(request.id)
                    setIsOffline(false)
                } catch (exception: Exception) {
                    if (!exception.becauseOfBadInternet()) {
                        when (request) {
                            is Request.SetCounter -> {
                                val requestWithException = request.copy(errorCode = exception.tryGetErrorCode())
                                offlineLocalDataSource.updateRequest(requestWithException.toEntity())
                            }
                            is Request.UpdateTask -> {
                                val requestWithException = request.copy(errorCode = exception.tryGetErrorCode())
                                offlineLocalDataSource.updateRequest(requestWithException.toEntity())
                            }
                        }
                    } else {
                        setIsOffline(true)
                        return@forEach
                    }
                }
            }
        }
    }

    suspend fun saveRequest(request: Request) {
        offlineLocalDataSource.saveRequest(request.toEntity())
    }
}