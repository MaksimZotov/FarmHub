package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.TrailingUnit

class TrailingUnitConverter {

    @TypeConverter
    fun toTrailingUnit(value: String): TrailingUnit {
        val typeToken = object : TypeToken<TrailingUnit>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromTrailingUnit(field: TrailingUnit): String {
        return Gson().toJson(field)
    }
}