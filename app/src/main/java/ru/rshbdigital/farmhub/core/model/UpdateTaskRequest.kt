package ru.rshbdigital.farmhub.core.model

import ru.rshbdigital.farmhub.core.api.converter.DateConverter

data class UpdateTaskRequest(
    val id: String?,
    val addDate: String?,
    val commitDate: String?,
    val operation: String?,
    val status: String?,
    val payment: Float?,
    val machine: String?,
    val unit: String?,
    val location: String?,
    val executor: String?,
    val comment: String?,
    val author: String?
)

fun Task.toUpdateTaskRequest() = UpdateTaskRequest(
    id = id,
    addDate = addDate?.let { DateConverter.toNetwork(it) },
    commitDate = commitDate?.let { DateConverter.toNetwork(it) },
    operation = when (operation) {
        is Operation.Seeding -> "SEEDING"
        is Operation.Protection -> "PROTECTION"
        is Operation.Harvesting -> "HARVESTING"
        is Operation.SoilPreparation -> "SOIL_PREPARATION"
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