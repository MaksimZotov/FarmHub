package ru.rshbdigital.farmhub.core.model

import ru.rshbdigital.farmhub.core.api.converter.DateConverter
import ru.rshbdigital.farmhub.core.api.converter.OperationConverter

data class UpdateTaskRequest(
    val id: String?,
    val addDate: String?,
    val commitDate: String?,
    val operation: Operation?,
    val status: String?,
    val payment: Float?,
    val machine: String?,
    val unit: String?,
    val location: String?,
    val executor: String?,
    val comment: String?,
    val author: String?
) {
    data class Operation(
        val type: String? = null,
        val speed: Float? = null,
        val depth: Float? = null,
        val flowRate: Float? = null,
    )
}

fun Task.toUpdateTaskRequest() = UpdateTaskRequest(
    id = id,
    addDate = addDate?.let { DateConverter.toNetwork(it) },
    commitDate = commitDate?.let { DateConverter.toNetwork(it) },
    operation = when (operation.type) {
        OperationConverter.Type.SEEDING -> UpdateTaskRequest.Operation(
            type = operation.type.operationName,
            speed = operation.speed,
            depth = operation.depth
        )
        OperationConverter.Type.PROTECTION -> UpdateTaskRequest.Operation(
            type = operation.type.operationName,
            speed = operation.speed,
            flowRate = operation.flowRate
        )
        OperationConverter.Type.HARVESTING -> UpdateTaskRequest.Operation(
            type = operation.type.operationName,
            speed = operation.speed,
            depth = operation.depth
        )
        OperationConverter.Type.SOIL_PREPARATION -> UpdateTaskRequest.Operation(
            type = operation.type.operationName,
            speed = operation.speed,
            depth = operation.depth
        )
    },
    status = status.name,
    payment = payment,
    machine = machine.id,
    unit = unit.id,
    location = location.id,
    executor = executor.id,
    comment = comment,
    author = author.id
)