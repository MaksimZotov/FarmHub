package ru.rshbdigital.farmhub.core.mapper

import ru.rshbdigital.farmhub.core.database.model.DBRequest
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.model.RequestType

fun Request.toEntity() = when (this) {
    is Request.SetCounter -> {
        DBRequest(
            id = id,
            requestType = RequestType.SET_COUNTER,
            errorCode = errorCode,
            count = count
        )
    }
}

fun DBRequest.toDomain() = when (this.requestType) {
    RequestType.SET_COUNTER -> Request.SetCounter(
        id = id,
        errorCode = errorCode,
        count = count ?: 0
    )
}