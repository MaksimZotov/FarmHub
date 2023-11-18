package ru.rshbdigital.farmhub.model

import java.util.Date

data class Task(
    val id: String,
    val taskAddedDate: Date,
    val commitDate: Date,
    val operation: Operation,
    val status: Status,
    val payment: Float,
    val machine: Machine,
    val unit: TrailingUnit,
    val location: Location,
    val executor: User,
    val comment: String?,
)
