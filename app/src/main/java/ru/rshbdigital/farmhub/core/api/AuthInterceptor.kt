package ru.rshbdigital.farmhub.core.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.rshbdigital.farmhub.client.login.AuthTokenStorage

class AuthInterceptor(
    private val authTokenStorage: AuthTokenStorage,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = authTokenStorage.currentAuthToken
        val request = originalRequest.newBuilder()
            .apply { if (!token.isNullOrBlank()) header("Authorization", "Token $token") }
            .build()
        return chain.proceed(request)
    }
}
