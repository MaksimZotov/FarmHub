package ru.rshbdigital.farmhub.core.api.model

data class NWTask(
    val id: String? = null,
    val taskAddedDate: String? = null,
    val commitDate: String? = null,
    val operation: NWOperation? = null,
    val status: String? = null,
    val payment: Float? = null,
    val machine: NWMachine? = null,
    val unit: NWTrailingUnit? = null,
    val location: NWLocation? = null,
    val executor: NWUser? = null,
    val comment: String? = null,
)
