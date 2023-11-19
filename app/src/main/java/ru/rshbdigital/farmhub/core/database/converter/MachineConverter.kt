package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.Machine

class MachineConverter {

    @TypeConverter
    fun toMachine(value: String): Machine {
        val typeToken = object : TypeToken<Machine>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromMachine(field: Machine): String {
        return Gson().toJson(field)
    }
}