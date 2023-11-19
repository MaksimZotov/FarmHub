package ru.rshbdigital.farmhub.core.model

import java.util.Date

data class Task(
    val id: String,
    val addDate: Date?,
    val commitDate: Date?,
    val operation: Operation,
    val status: Status,
    val payment: Float?,
    val machine: Machine,
    val unit: TrailingUnit,
    val location: Location,
    val executor: User,
    val comment: String?,
    val author: User
) {
    enum class Status {
        UNKNOWN,
        OPEN,
        MACHINE_INSPECTION,
        IN_PROGRESS,
        CLOSED,
        PAUSED,
        DONE,
    }
}
