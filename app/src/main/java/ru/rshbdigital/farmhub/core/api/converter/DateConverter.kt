package ru.rshbdigital.farmhub.core.api.converter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverter {

    private const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun fromNetwork(src: String): Date? {
        val sdf = SimpleDateFormat(PATTERN, Locale.getDefault())
        return sdf.parse(src)
    }

    fun toNetwork(src: Date): String {
        val sdf = SimpleDateFormat(PATTERN, Locale.getDefault())
        return sdf.format(src)
    }
}
