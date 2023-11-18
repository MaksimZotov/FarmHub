package ru.rshbdigital.farmhub.client.task

import ru.rshbdigital.farmhub.core.api.AgriculturalApi
import ru.rshbdigital.farmhub.core.api.converter.TaskConverter
import ru.rshbdigital.farmhub.core.model.Task

class TaskRepository(
    private val api: AgriculturalApi,
) {

    suspend fun updateTask(task: Task): Boolean =
        try {
            api.updateTask(TaskConverter.toNetwork(task))
            true
        } catch (e: Exception) {
            false
        }
    }
