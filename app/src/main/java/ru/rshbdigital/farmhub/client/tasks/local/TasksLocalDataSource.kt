package ru.rshbdigital.farmhub.client.tasks.local

import ru.rshbdigital.farmhub.core.model.Task
import javax.inject.Inject

class TasksLocalDataSource @Inject constructor(

) {
    suspend fun saveTasks(tasks: List<Task>) {

    }

    suspend fun getAllTasks(): List<Task> {
        return emptyList()
    }
}