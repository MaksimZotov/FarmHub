package ru.rshbdigital.farmhub.core.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import ru.rshbdigital.farmhub.core.api.model.NWFcmTokenRequest
import ru.rshbdigital.farmhub.core.api.model.NWPaginationList
import ru.rshbdigital.farmhub.core.api.model.NWRfidRequest
import ru.rshbdigital.farmhub.core.api.model.NWRfidResponse
import ru.rshbdigital.farmhub.core.api.model.NWTask

interface NetworkApi {

    @POST("rfid_auth/")
    suspend fun loginByRfid(@Body rfid: NWRfidRequest): NWRfidResponse

    @POST("fcm_token/")
    suspend fun sendFcmToken(@Body fcmToken: NWFcmTokenRequest)

    @GET("task/")
    suspend fun getTasks(
        @Query("page") page: Int
    ): NWPaginationList<NWTask>

    @PUT("task/{id}/")
    suspend fun updateTask(
        @Path("id") id: String,
        @Body task: NWTask
    ): NWTask
}
