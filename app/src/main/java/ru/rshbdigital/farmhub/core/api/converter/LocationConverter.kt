package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWLocation
import ru.rshbdigital.farmhub.core.model.Location
import ru.rshbdigital.farmhub.core.util.nullIfBlank

object LocationConverter {

    fun fromNetwork(src: NWLocation): Location? = src.name?.nullIfBlank()?.let { name ->
        Location(
            name = name,
            lat = src.lat,
            lng = src.lng,
        )
    }

    fun toNetwork(src: Location): NWLocation = NWLocation(
        name = src.name,
        lat = src.lat,
        lng = src.lng,
    )
}
