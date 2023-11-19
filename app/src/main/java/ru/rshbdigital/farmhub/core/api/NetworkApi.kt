package ru.rshbdigital.farmhub.core.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import ru.rshbdigital.farmhub.core.api.model.NWPaginationList
import ru.rshbdigital.farmhub.core.api.model.NWTask

interface NetworkApi {

    @GET("task/")
    suspend fun getTasks(
        @Query("page") page: Int
    ): NWPaginationList<NWTask>

    @PUT("task/{id}/")
    suspend fun updateTask(
        @Path("id") id: String,
        @Body task: NWTask
    ): NWTask

    @POST("rfid_auth/")
    suspend fun rfid(
        @Body rfid: Rfid
    )

    data class Rfid(
        val rfid: String
    )
}
