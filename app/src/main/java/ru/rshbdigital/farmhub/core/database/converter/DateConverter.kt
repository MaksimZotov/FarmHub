package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    private companion object {
        const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }

    @TypeConverter
    fun toDate(value: String): Date? {
        val sdf = SimpleDateFormat(PATTERN, Locale.getDefault())
        return sdf.parse(value)
    }

    @TypeConverter
    fun fromDate(field: Date): String {
        val sdf = SimpleDateFormat(PATTERN, Locale.getDefault())
        return sdf.format(field)
    }
}