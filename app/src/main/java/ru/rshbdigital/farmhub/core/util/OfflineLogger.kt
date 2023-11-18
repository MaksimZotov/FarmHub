package ru.rshbdigital.farmhub.core.util

import timber.log.Timber

object OfflineLogger {
    fun log(message: String) {
        Timber.d("OfflineLogger: $message")
    }
}