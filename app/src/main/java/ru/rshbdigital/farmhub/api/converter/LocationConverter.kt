package ru.rshbdigital.farmhub.api.converter

import ru.rshbdigital.farmhub.api.model.NWLocation
import ru.rshbdigital.farmhub.model.Location
import ru.rshbdigital.farmhub.util.nullIfBlank

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
