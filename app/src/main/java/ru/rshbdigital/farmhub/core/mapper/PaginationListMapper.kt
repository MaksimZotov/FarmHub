package ru.rshbdigital.farmhub.core.mapper

import ru.rshbdigital.farmhub.core.api.model.NWPaginationList
import ru.rshbdigital.farmhub.core.model.PaginationList

fun <T, V> NWPaginationList<T>.toDomain(
    map: (T) -> V
) = PaginationList(
    next = next,
    results = results?.map(map)
)