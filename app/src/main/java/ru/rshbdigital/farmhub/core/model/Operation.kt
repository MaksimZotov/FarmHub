package ru.rshbdigital.farmhub.core.model

import ru.rshbdigital.farmhub.core.api.converter.OperationConverter

data class Operation(
    val type: OperationConverter.Type,
    val speed: Float = 0f,
    val depth: Float = 0f,
    val flowRate: Float = 0f,
)
