package ru.rshbdigital.farmhub.core.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "Token aad2993e42a87e26e0eeb7a9dde2c82832ca211f")
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}