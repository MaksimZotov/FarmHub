package ru.rshbdigital.farmhub.core.mapper

import ru.rshbdigital.farmhub.core.api.model.NWCounter
import ru.rshbdigital.farmhub.core.database.model.DBCounter
import ru.rshbdigital.farmhub.core.model.Counter

fun NWCounter.toDomain() = Counter(
    count = count ?: 0
)

fun DBCounter.toDomain() = Counter(
    count = count ?: 0
)

fun Counter.toEntity() = DBCounter(
    count = count
)