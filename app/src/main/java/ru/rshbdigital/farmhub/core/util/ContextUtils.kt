package ru.rshbdigital.farmhub.core.util

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

internal inline fun <reified T> Context.getActivityAs(): T? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is ComponentActivity) {
            return currentContext as? T
        }
        currentContext = currentContext.baseContext
    }
    return null
}
