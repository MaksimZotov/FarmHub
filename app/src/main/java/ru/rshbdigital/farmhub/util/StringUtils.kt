package ru.rshbdigital.farmhub.util

fun String?.nullIfBlank(): String? = if (isNullOrBlank()) null else this
