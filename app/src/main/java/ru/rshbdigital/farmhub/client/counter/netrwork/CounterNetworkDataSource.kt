package ru.rshbdigital.farmhub.client.counter.netrwork

import kotlinx.coroutines.delay
import ru.rshbdigital.farmhub.core.api.model.NWCounter
import ru.rshbdigital.farmhub.core.util.OfflineLogger
import java.io.IOException
import javax.inject.Inject

class CounterNetworkDataSource @Inject constructor() {

    private var countForTest = 0

    suspend fun getCounter(count: Int): NWCounter {
        OfflineLogger.log("getCounter network start $count")
        delay(2000)
        OfflineLogger.log("getCounter network end $count")
        return NWCounter(count = count)
    }

    suspend fun setCounter(count: Int): NWCounter {
        OfflineLogger.log("setCounter network start $count")
        countForTest++
        if (countForTest == 3) {
            countForTest = 0
            throw IOException()
        }
        delay(2000)
        OfflineLogger.log("setCounter network end $count")
        return NWCounter(count = count)
    }
}