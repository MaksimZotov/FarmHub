package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWLocation
import ru.rshbdigital.farmhub.core.model.Location
import ru.rshbdigital.farmhub.core.util.let

object LocationConverter {

    fun fromNetwork(src: NWLocation): Location? = let(src.id, src.name) { id, name ->
        Location(
            id = id,
            name = name,
            lat = src.lat,
            lng = src.lng,
        )
    }

    fun toNetwork(src: Location): NWLocation = NWLocation(
        id = src.id,
        name = src.name,
        lat = src.lat,
        lng = src.lng,
    )
}
