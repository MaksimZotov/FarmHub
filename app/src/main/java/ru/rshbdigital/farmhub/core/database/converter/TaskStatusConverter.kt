package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.Task

class TaskStatusConverter {

    @TypeConverter
    fun toTaskStatus(value: String): Task.Status {
        val typeToken = object : TypeToken<Task.Status>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromTaskStatus(field: Task.Status): String {
        return Gson().toJson(field)
    }
}