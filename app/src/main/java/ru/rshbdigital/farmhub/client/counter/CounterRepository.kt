package ru.rshbdigital.farmhub.client.counter

import ru.rshbdigital.farmhub.client.counter.local.CounterLocalDataSource
import ru.rshbdigital.farmhub.client.counter.netrwork.CounterNetworkDataSource
import ru.rshbdigital.farmhub.client.offline.OfflineRepository
import ru.rshbdigital.farmhub.core.mapper.toDomain
import ru.rshbdigital.farmhub.core.mapper.toEntity
import ru.rshbdigital.farmhub.core.model.Counter
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.util.becauseOfBadInternet
import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val offlineRepository: OfflineRepository,
    private val counterLocalDataSource: CounterLocalDataSource,
    private val counterNetworkDataSource: CounterNetworkDataSource
) {

    suspend fun setCounter(count: Int): Counter {
       return try {
           if (offlineRepository.checkIsOffline()) {
               performSetCounterLocally(count)
           } else {
               val counter = counterNetworkDataSource.setCounter(count).toDomain()
               offlineRepository.setIsOffline(false)
               counter
           }
       } catch (exception: Exception) {
           if (exception.becauseOfBadInternet()) {
               offlineRepository.setIsOffline(true)
               performSetCounterLocally(count)
           } else {
               offlineRepository.setIsOffline(false)
               throw exception
           }
       }
    }

    suspend fun getCounter(count: Int): Counter {
        return try {
            val counter = counterNetworkDataSource.getCounter(count).toDomain()
            counterLocalDataSource.saveCounter(counter.toEntity())
            offlineRepository.setIsOffline(false)
            return counter
        } catch (exception: Exception) {
            if (exception.becauseOfBadInternet()) {
                offlineRepository.setIsOffline(true)
                counterLocalDataSource.getCounter(count).toDomain()
            } else {
                offlineRepository.setIsOffline(false)
                throw exception
            }
        }
    }

    private suspend fun performSetCounterLocally(count: Int): Counter {
        val counter = Counter(
            count = count
        )
        counterLocalDataSource.saveCounter(
            dbCounter = counter.toEntity()
        )
        offlineRepository.saveRequest(
            request = Request.SetCounter(
                count = count
            )
        )
        return counter
    }
}