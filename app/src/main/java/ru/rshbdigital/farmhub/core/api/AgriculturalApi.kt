package ru.rshbdigital.farmhub.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rshbdigital.farmhub.core.api.model.NWTask

interface AgriculturalApi {

    suspend fun updateTask(task: NWTask): NWTask

    companion object {
        private const val BASE_URL = "TODO"

        fun create(): AgriculturalApi {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AgriculturalApi::class.java)
        }
    }
}
