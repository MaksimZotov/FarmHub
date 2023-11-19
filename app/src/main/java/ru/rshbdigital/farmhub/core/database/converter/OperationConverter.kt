package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.Operation

class OperationConverter {

    @TypeConverter
    fun toOperation(value: String): Operation {
        val typeToken = object : TypeToken<Operation>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromOperation(field: Operation): String {
        return Gson().toJson(field)
    }
}