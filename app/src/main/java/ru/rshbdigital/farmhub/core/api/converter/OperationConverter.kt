package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWOperation
import ru.rshbdigital.farmhub.core.model.Operation
import ru.rshbdigital.farmhub.core.util.let

object OperationConverter {

    private const val SEEDING = "SEEDING"
    private const val PROTECTION = "PROTECTION"
    private const val HARVESTING = "HARVESTING"
    private const val SOIL_PREPARATION = "SOIL_PREPARATION"

    fun fromNetwork(src: NWOperation): Operation? = when (src.type) {
        SEEDING -> let(src.speed, src.depth) { speed, depth ->
            Operation.Seeding(
                depth = depth,
                speed = speed,
            )
        }

        PROTECTION -> let(src.speed, src.flowRate) { speed, flowRate ->
            Operation.Protection(
                flowRate = flowRate,
                speed = speed,
            )
        }

        HARVESTING -> let(src.speed, src.depth) { speed, depth ->
            Operation.Harvesting(
                depth = depth,
                speed = speed,
            )
        }

        SOIL_PREPARATION -> let(src.speed, src.depth) { speed, depth ->
            Operation.SoilPreparation(
                depth = depth,
                speed = speed,
            )
        }

        else -> null
    }

    fun toNetwork(src: Operation): NWOperation {
        val type = when (src) {
            is Operation.Seeding -> SEEDING
            is Operation.Protection -> PROTECTION
            is Operation.Harvesting -> HARVESTING
            is Operation.SoilPreparation -> SOIL_PREPARATION
        }

        return NWOperation(
            type = type,
            speed = src.speed,
            depth = when (src) {
                is Operation.Seeding -> src.depth
                is Operation.Protection -> null
                is Operation.Harvesting -> src.depth
                is Operation.SoilPreparation -> src.depth
            },
            flowRate = when (src) {
                is Operation.Protection -> src.flowRate
                else -> null
            },
        )
    }
}
