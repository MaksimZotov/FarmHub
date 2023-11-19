package ru.rshbdigital.farmhub.core.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.rshbdigital.farmhub.core.api.model.NWRfidRequest
import ru.rshbdigital.farmhub.core.api.model.NWRfidResponse
import ru.rshbdigital.farmhub.core.api.model.NWTask

interface NetworkApi {

    @POST("rfid_auth/")
    suspend fun loginByRfid(@Body rfid: NWRfidRequest): NWRfidResponse

    suspend fun updateTask(task: NWTask): NWTask
}
