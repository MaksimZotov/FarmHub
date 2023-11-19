package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.model.User

class UserConverter {

    @TypeConverter
    fun toUser(value: String): User {
        val typeToken = object : TypeToken<User>() {}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromUser(field: User): String {
        return Gson().toJson(field)
    }
}