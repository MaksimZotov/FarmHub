package ru.rshbdigital.farmhub.api.converter

import ru.rshbdigital.farmhub.api.model.NWMachine
import ru.rshbdigital.farmhub.model.Machine
import ru.rshbdigital.farmhub.util.let
import ru.rshbdigital.farmhub.util.nullIfBlank

object MachineConverter {

    fun fromNetwork(src: NWMachine): Machine? = let(
        src.id.nullIfBlank(),
        src.name.nullIfBlank(),
        src.registrationNumber.nullIfBlank(),
    ) { id, name, registrationNumber ->
        Machine(
            id = id,
            name = name,
            registrationNumber = registrationNumber,
        )
    }

    fun toNetwork(src: Machine): NWMachine = NWMachine(
        id = src.id,
        name = src.name,
        registrationNumber = src.registrationNumber,
    )
}
