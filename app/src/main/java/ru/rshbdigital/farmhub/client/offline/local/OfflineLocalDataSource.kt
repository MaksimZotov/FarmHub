package ru.rshbdigital.farmhub.client.offline.local

import kotlinx.coroutines.flow.Flow
import ru.rshbdigital.farmhub.core.database.dao.RequestDao
import ru.rshbdigital.farmhub.core.database.model.DBRequest
import ru.rshbdigital.farmhub.core.util.OfflineLogger
import javax.inject.Inject

class OfflineLocalDataSource @Inject constructor(
    private val requestDao: RequestDao
) {

    fun getAllRequestsFlow(): Flow<List<DBRequest>> = requestDao.getAllFlow()

    suspend fun getAllRequests(): List<DBRequest> = requestDao.getAll()

    suspend fun saveRequest(request: DBRequest) {
        OfflineLogger.log("saveRequest $request")
        requestDao.save(request)
    }

    suspend fun updateRequest(request: DBRequest) {
        OfflineLogger.log("updateRequest $request")
        requestDao.update(request)
    }

    suspend fun deleteRequest(requestId: Long) {
        OfflineLogger.log("deleteRequest $requestId")
        requestDao.delete(requestId)
    }
}