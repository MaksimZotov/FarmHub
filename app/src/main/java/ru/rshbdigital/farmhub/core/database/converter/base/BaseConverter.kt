package ru.rshbdigital.farmhub.core.database.converter.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class BaseConverter <T> {

    @TypeConverter
    fun to(value: String): T {
        val typeToken = object : TypeToken<T>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun from(field: T): String {
       return Gson().toJson(field)
    }
}