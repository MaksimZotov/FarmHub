package ru.rshbdigital.farmhub.client.login

import kotlinx.coroutines.flow.MutableStateFlow
import ru.rshbdigital.farmhub.core.model.User
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    // Можно тянуть из DataStore
    val userRole = MutableStateFlow(User.Role.AGRICULTURIST)
}