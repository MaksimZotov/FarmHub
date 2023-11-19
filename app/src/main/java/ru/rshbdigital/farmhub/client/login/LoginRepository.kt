package ru.rshbdigital.farmhub.client.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.rshbdigital.farmhub.core.api.NetworkApi
import ru.rshbdigital.farmhub.core.api.model.NWFcmTokenRequest
import ru.rshbdigital.farmhub.core.api.model.NWRfidRequest
import ru.rshbdigital.farmhub.core.model.AuthTokenHolder
import ru.rshbdigital.farmhub.core.model.User
import ru.rshbdigital.farmhub.core.util.nullIfBlank
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: NetworkApi,
    private val authTokenStorage: AuthTokenStorage,
) {
    // Можно тянуть из DataStore
    val userRole = MutableStateFlow(User.Role.MECHANIZER)

    val isAuthorized: Flow<Boolean> = authTokenStorage.authToken.map { it != null }

    suspend fun loginByRfid(rfid: String): AuthTokenHolder = withContext(Dispatchers.IO) {
        val response = api.loginByRfid(NWRfidRequest(rfid = rfid))
        val token = response.Token?.nullIfBlank()
        authTokenStorage.setAuthToken(token)
        checkNotNull(token)
        AuthTokenHolder(
            token = token,
        )
    }

    suspend fun sendFcmToken(token: String) = withContext(Dispatchers.IO){
        api.sendFcmToken(NWFcmTokenRequest(token = token))
    }
}
