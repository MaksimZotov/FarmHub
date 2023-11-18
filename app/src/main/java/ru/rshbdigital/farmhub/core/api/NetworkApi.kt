package ru.rshbdigital.farmhub.core.api

import ru.rshbdigital.farmhub.core.api.model.NWPaginationList
import ru.rshbdigital.farmhub.core.api.model.NWTask

interface NetworkApi {

    suspend fun getTasks(page: Int): NWPaginationList<NWTask>

    suspend fun updateTask(task: NWTask): NWTask
}
