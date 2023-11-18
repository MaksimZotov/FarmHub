package ru.rshbdigital.farmhub.data

import ru.rshbdigital.farmhub.model.Task

interface TaskRepository {

    suspend fun updateTask(task: Task): Boolean
}
