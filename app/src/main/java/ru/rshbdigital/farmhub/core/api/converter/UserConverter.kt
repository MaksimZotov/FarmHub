package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWUser
import ru.rshbdigital.farmhub.core.model.User
import ru.rshbdigital.farmhub.core.util.let
import ru.rshbdigital.farmhub.core.util.nullIfBlank

object UserConverter {

    fun fromNetwork(src: NWUser): User? = let(
        src.id.nullIfBlank(),
        src.firstName.nullIfBlank(),
        src.lastName.nullIfBlank(),
    ) { id, firstName, lastName ->
        User(
            id = id,
            rfid = src.rfid,
            firstName = firstName,
            lastName = lastName,
            middleName = src.middleName,
            employeeId = src.employeeId,
            role = convertEnum<User.Role>(src.role) ?: User.Role.UNKNOWN,
            phone = src.phone,
        )
    }

    fun toNetwork(src: User): NWUser = NWUser(
        id = src.id,
        rfid = src.rfid,
        firstName = src.firstName,
        lastName = src.lastName,
        middleName = src.middleName,
        employeeId = src.employeeId,
        role = src.role.name,
        phone = src.phone,
    )
}
