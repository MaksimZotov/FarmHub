package ru.rshbdigital.farmhub.client.counter.local

import kotlinx.coroutines.delay
import ru.rshbdigital.farmhub.core.database.model.DBCounter
import ru.rshbdigital.farmhub.core.util.OfflineLogger
import javax.inject.Inject

class CounterLocalDataSource @Inject constructor() {

    suspend fun getCounter(count: Int): DBCounter {
        OfflineLogger.log("getCounter local start $count")
        delay(200)
        OfflineLogger.log("getCounter local end $count")
        return DBCounter(count)
    }

    suspend fun saveCounter(dbCounter: DBCounter) {
        OfflineLogger.log("saveCounter local start $dbCounter")
        delay(200)
        OfflineLogger.log("saveCounter local end $dbCounter")
    }
}