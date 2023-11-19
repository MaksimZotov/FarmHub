package ru.rshbdigital.farmhub.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.formatTo(pattern: String): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    formatter.timeZone = TimeZone.getDefault()
    return formatter.format(this)
}
