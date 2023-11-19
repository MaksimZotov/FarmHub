package ru.rshbdigital.farmhub.core.api.model

data class NWPaginationList<T>(
    val next: Int?,
    val results: List<T>?
)