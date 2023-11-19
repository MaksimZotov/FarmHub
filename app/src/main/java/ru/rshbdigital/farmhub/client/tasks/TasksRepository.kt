package ru.rshbdigital.farmhub.client.tasks

import ru.rshbdigital.farmhub.client.offline.OfflineRepository
import ru.rshbdigital.farmhub.client.tasks.local.TasksLocalDataSource
import ru.rshbdigital.farmhub.client.tasks.network.TasksNetworkDataSource
import ru.rshbdigital.farmhub.core.api.converter.TaskConverter
import ru.rshbdigital.farmhub.core.mapper.toDomain
import ru.rshbdigital.farmhub.core.model.PaginationList
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.model.UpdateTaskRequest
import ru.rshbdigital.farmhub.core.util.becauseOfBadInternet
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val offlineRepository: OfflineRepository,
    private val tasksLocalDataSource: TasksLocalDataSource,
    private val tasksNetworkDataSource: TasksNetworkDataSource
) {

    suspend fun getTasks(page: Int): PaginationList<Task> {
        return try {
            offlineRepository.trySendAllRequests()
            val tasks = tasksNetworkDataSource
                .getTasks(page)
                .toDomain(TaskConverter::fromNetwork)
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

    suspend fun updateTask(task: Task, updateTaskRequest: UpdateTaskRequest): Task? {
        return try {
            if (offlineRepository.checkIsOffline()) {
                performUpdateTaskLocally(task, updateTaskRequest)
            } else {
                val newTask = tasksNetworkDataSource.updateTask(task.id, updateTaskRequest)
                val convertedTask = TaskConverter.fromNetwork(newTask)
                offlineRepository.setIsOffline(false)
                convertedTask
            }
        } catch (exception: Exception) {
            if (exception.becauseOfBadInternet()) {
                offlineRepository.setIsOffline(true)
                performUpdateTaskLocally(task, updateTaskRequest)
            } else {
                offlineRepository.setIsOffline(false)
                throw exception
            }
        }
    }

    private suspend fun performUpdateTaskLocally(
        task: Task,
        updateTaskRequest: UpdateTaskRequest
    ): Task {
        val convertedTask = TaskConverter.toEntity(task)
        tasksLocalDataSource.saveTask(convertedTask)
        offlineRepository.saveRequest(
            request = Request.UpdateTask(
                taskId = task.id,
                updateTaskRequest = updateTaskRequest
            )
        )
        return task
    }
}
