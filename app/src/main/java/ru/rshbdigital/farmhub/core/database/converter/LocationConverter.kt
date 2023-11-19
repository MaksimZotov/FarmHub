package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.Location

class LocationConverter {

    @TypeConverter
    fun toLocation(value: String): Location {
        val typeToken = object : TypeToken<Location>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromLocation(field: Location): String {
        return Gson().toJson(field)
    }
}