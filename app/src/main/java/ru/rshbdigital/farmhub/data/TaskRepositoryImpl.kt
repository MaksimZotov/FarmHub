package ru.rshbdigital.farmhub.data

import ru.rshbdigital.farmhub.api.AgriculturalApi
import ru.rshbdigital.farmhub.api.converter.TaskConverter
import ru.rshbdigital.farmhub.model.Task

class TaskRepositoryImpl(
    private val api: AgriculturalApi,
) : TaskRepository {

    override suspend fun updateTask(task: Task): Boolean =
        try {
            api.updateTask(TaskConverter.toNetwork(task))
            true
        } catch (e: Exception) {
            false
        }
    }
