package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWOperation
import ru.rshbdigital.farmhub.core.model.Operation
import ru.rshbdigital.farmhub.core.util.let

object OperationConverter {

    enum class Type(val operationName: String) {
        SEEDING("SEEDING"),
        PROTECTION("PROTECTION"),
        HARVESTING("HARVESTING"),
        SOIL_PREPARATION("SOIL_PREPARATION"),
    }

    fun fromNetwork(src: NWOperation): Operation? = when (src.type) {
        Type.SEEDING.operationName -> let(src.speed, src.depth) { speed, depth ->
            Operation(
                type = Type.SEEDING,
                depth = depth,
                speed = speed,
            )
        }

        Type.PROTECTION.operationName -> let(src.speed, src.flowRate) { speed, flowRate ->
            Operation(
                type = Type.PROTECTION,
                flowRate = flowRate,
                speed = speed,
            )
        }

        Type.HARVESTING.operationName -> let(src.speed, src.depth) { speed, depth ->
            Operation(
                type = Type.HARVESTING,
                depth = depth,
                speed = speed,
            )
        }

        Type.SOIL_PREPARATION.operationName -> let(src.speed, src.depth) { speed, depth ->
            Operation(
                type = Type.SOIL_PREPARATION,
                depth = depth,
                speed = speed,
            )
        }

        else -> null
    }
}
