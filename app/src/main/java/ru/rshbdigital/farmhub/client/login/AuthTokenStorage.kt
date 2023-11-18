package ru.rshbdigital.farmhub.client.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val STORAGE_NAME = "AUTH_TOKEN_STORAGE"
private const val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"

private val Context.dataStoreSettings: DataStore<Preferences> by preferencesDataStore(STORAGE_NAME)

class AuthTokenStorage @Inject constructor(
    private val context: Context,
) {

    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN_KEY)

    var currentAuthToken: String? = null
        private set

    val authToken: Flow<String?> = context.dataStoreSettings.data.map { preferences ->
        val key = preferences[authTokenKey]
        currentAuthToken = key
        key
    }

    suspend fun setAuthToken(token: String?) = context.dataStoreSettings.edit { preferences ->
        if (token.isNullOrBlank()) {
            preferences.remove(authTokenKey)
        } else {
            preferences[authTokenKey] = token
        }
    }
}
