package ru.rshbdigital.farmhub.api.converter

inline fun <reified T : Enum<T>> convertEnum(src: String?): T? {
    src ?: return null
    return try {
        enumValueOf<T>(src)
    } catch (e: Exception) {
        null
    }
}

inline fun <A, B> convert(src: A?, block: (A) -> B?): B? =
    if (src != null) {
        block(src)
    } else {
        null
    }
