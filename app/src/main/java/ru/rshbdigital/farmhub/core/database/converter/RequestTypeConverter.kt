package ru.rshbdigital.farmhub.core.database.converter

import androidx.room.TypeConverter
import ru.rshbdigital.farmhub.core.model.RequestType

class RequestTypeConverter {

    @TypeConverter
    fun toRequestType(value: String): RequestType {
        return RequestType.valueOf(value)
    }

    @TypeConverter
    fun fromRequestType(requestType: RequestType): String {
        return requestType.name
    }}