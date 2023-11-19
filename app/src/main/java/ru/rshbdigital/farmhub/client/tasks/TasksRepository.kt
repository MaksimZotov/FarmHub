package ru.rshbdigital.farmhub.client.tasks

import androidx.room.TypeConverter
import ru.rshbdigital.farmhub.client.offline.OfflineRepository
import ru.rshbdigital.farmhub.client.tasks.local.TasksLocalDataSource
import ru.rshbdigital.farmhub.client.tasks.network.TasksNetworkDataSource
import ru.rshbdigital.farmhub.core.api.converter.TaskConverter
import ru.rshbdigital.farmhub.core.mapper.toDomain
import ru.rshbdigital.farmhub.core.model.PaginationList
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.util.becauseOfBadInternet
import timber.log.Timber
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val offlineRepository: OfflineRepository,
    private val tasksLocalDataSource: TasksLocalDataSource,
    private val tasksNetworkDataSource: TasksNetworkDataSource
) {

    suspend fun getTasks(page: Int): PaginationList<Task> {
        return try {
            val tasks = tasksNetworkDataSource
                .getTasks(page)
                .toDomain(TaskConverter::fromNetwork)
            Timber.d("FFFFF $tasks")
            val filteredTasks = PaginationList(
                next = tasks.next,
                results = tasks.results?.filterNotNull()
            )
            tasksLocalDataSource.saveTasks(
                tasks = filteredTasks.results.orEmpty().map { task ->
                    TaskConverter.toEntity(
                        src = task
                    )
                }
            )
            offlineRepository.setIsOffline(false)
            filteredTasks
        } catch (exception: Exception) {
            if (exception.becauseOfBadInternet()) {
                offlineRepository.setIsOffline(true)
                PaginationList(
                    next = null,
                    results = tasksLocalDataSource.getAllTasks().map { task ->
                        TaskConverter.fromEntity(
                            src = task
                        )
                    }
                )
            } else {
                offlineRepository.setIsOffline(false)
                throw exception
            }
        }
    }

    suspend fun updateTask(task: Task): Task? {
        return try {
            if (offlineRepository.checkIsOffline()) {
                performUpdateTaskLocally(task)
            } else {
                val newTask = TaskConverter.fromNetwork(
                    tasksNetworkDataSource.updateTask(
                        TaskConverter.toNetwork(task)
                    )
                )
                offlineRepository.setIsOffline(false)
                newTask
            }
        } catch (exception: Exception) {
            if (exception.becauseOfBadInternet()) {
                offlineRepository.setIsOffline(true)
                performUpdateTaskLocally(task)
            } else {
                offlineRepository.setIsOffline(false)
                throw exception
            }
        }
    }

    private suspend fun performUpdateTaskLocally(task: Task): Task {
        tasksLocalDataSource.saveTask(
            task = TaskConverter.toEntity(
                src = task
            )
        )
        offlineRepository.saveRequest(
            request = Request.UpdateTask(
                task = task
            )
        )
        return task
    }
}
