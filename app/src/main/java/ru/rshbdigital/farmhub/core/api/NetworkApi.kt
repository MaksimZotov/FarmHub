package ru.rshbdigital.farmhub.core.api

import ru.rshbdigital.farmhub.core.api.model.NWTask

interface NetworkApi {

    suspend fun updateTask(task: NWTask): NWTask
}
