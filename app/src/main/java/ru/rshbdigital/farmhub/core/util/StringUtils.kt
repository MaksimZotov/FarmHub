package ru.rshbdigital.farmhub.core.util

fun String?.nullIfBlank(): String? = if (isNullOrBlank()) null else this
