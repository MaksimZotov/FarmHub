package ru.rshbdigital.farmhub.core.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rshbdigital.farmhub.core.database.model.DBRequest
import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.model.RequestType

fun Request.toEntity() = when (this) {
    is Request.SetCounter -> {
        DBRequest(
            id = id,
            requestType = RequestType.SET_COUNTER,
            errorCode = errorCode,
            request = Gson().toJson(this)
        )
    }
    is Request.UpdateTask -> {
        DBRequest(
            id = id,
            requestType = RequestType.UPDATE_TASK,
            errorCode = errorCode,
            request = Gson().toJson(this)
        )
    }
}

fun DBRequest.toDomain() = when (requestType) {
    RequestType.SET_COUNTER -> {
        val typeToken = object : TypeToken<Request.SetCounter>() {}.type
        val request = Gson().fromJson<Request.SetCounter>(request, typeToken)
        Request.SetCounter(
            id = id,
            errorCode = errorCode,
            count = request.count
        )
    }
    RequestType.UPDATE_TASK -> {
        val typeToken = object : TypeToken<Request.UpdateTask>() {}.type
        val request = Gson().fromJson<Request.UpdateTask>(request, typeToken)
        Request.UpdateTask(
            id = id,
            errorCode = errorCode,
            taskId = request.taskId,
            updateTaskRequest = request.updateTaskRequest
        )
    }
}