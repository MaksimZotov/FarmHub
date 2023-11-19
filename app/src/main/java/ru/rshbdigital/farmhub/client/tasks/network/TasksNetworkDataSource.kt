package ru.rshbdigital.farmhub.client.tasks.network

import ru.rshbdigital.farmhub.core.api.NetworkApi
import ru.rshbdigital.farmhub.core.api.model.NWPaginationList
import ru.rshbdigital.farmhub.core.api.model.NWTask
import javax.inject.Inject

class TasksNetworkDataSource @Inject constructor(
    private val api: NetworkApi,
) {
    suspend fun getTasks(page: Int): NWPaginationList<NWTask> {
        //api.rfid(NetworkApi.Rfid("04:25:58:ba:86:3f:80"))
        return api.getTasks(page)
    }

    suspend fun updateTask(task: NWTask): NWTask {
        return api.updateTask(task.id.orEmpty(), task)
    }
}