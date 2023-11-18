package ru.rshbdigital.farmhub.api.converter

import ru.rshbdigital.farmhub.api.model.NWTrailingUnit
import ru.rshbdigital.farmhub.model.TrailingUnit
import ru.rshbdigital.farmhub.util.let
import ru.rshbdigital.farmhub.util.nullIfBlank

object TrailingUnitConverter {

    fun fromNetwork(src: NWTrailingUnit): TrailingUnit? = let(
        src.id.nullIfBlank(),
        src.name.nullIfBlank(),
        src.serialNumber.nullIfBlank(),
    ) { id, name, serialNumber ->
        TrailingUnit(
            id = id,
            name = name,
            serialNumber = serialNumber,
        )
    }

    fun toNetwork(src: TrailingUnit): NWTrailingUnit = NWTrailingUnit(
        id = src.id,
        name = src.name,
        serialNumber = src.serialNumber,
    )
}
