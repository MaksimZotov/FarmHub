package ru.rshbdigital.farmhub.core.model

data class PaginationList<T>(
    val next: Int?,
    val results: List<T>?
)