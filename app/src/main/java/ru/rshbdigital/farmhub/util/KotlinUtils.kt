package ru.rshbdigital.farmhub.util

inline fun <A, B, R> let(first: A?, second: B?, block: (A, B) -> R): R? {
    return if (first != null && second != null) {
        block(first, second)
    } else {
        null
    }
}

inline fun <A, B, C, R> let(first: A?, second: B?, third: C?, block: (A, B, C) -> R): R? {
    return if (first != null && second != null && third != null) {
        block(first, second, third)
    } else {
        null
    }
}

inline fun <A, B, C, D, R> let(first: A?, second: B?, third: C?, fourth: D?, block: (A, B, C, D) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null) {
        block(first, second, third, fourth)
    } else {
        null
    }
}

inline fun <A, B, C, D, E, R> let(first: A?, second: B?, third: C?, fourth: D?, fifth: E?, block: (A, B, C, D, E) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null && fifth != null) {
        block(first, second, third, fourth, fifth)
    } else {
        null
    }
}

inline fun <A, B, C, D, E, F, R> let(first: A?, second: B?, third: C?, fourth: D?, fifth: E?, sixth: F?, block: (A, B, C, D, E, F) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null) {
        block(first, second, third, fourth, fifth, sixth)
    } else {
        null
    }
}
