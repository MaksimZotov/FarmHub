package ru.rshbdigital.farmhub.core.util

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

inline fun <A, B, C, D, E, F, G, R> let(first: A?, second: B?, third: C?, fourth: D?, fifth: E?, sixth: F?, seventh: G?, block: (A, B, C, D, E, F, G) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null && seventh != null) {
        block(first, second, third, fourth, fifth, sixth, seventh)
    } else {
        null
    }
}

inline fun <A, B, C, D, E, F, G, H, R> let(first: A?, second: B?, third: C?, fourth: D?, fifth: E?, sixth: F?, seventh: G?, eighth: H?, block: (A, B, C, D, E, F, G, H) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null && seventh != null && eighth != null) {
        block(first, second, third, fourth, fifth, sixth, seventh, eighth)
    } else {
        null
    }
}

inline fun <A, B, C, D, E, F, G, H, I, R> let(first: A?, second: B?, third: C?, fourth: D?, fifth: E?, sixth: F?, seventh: G?, eighth: H?, ninth: I?, block: (A, B, C, D, E, F, G, H, I) -> R): R? {
    return if (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null && seventh != null && eighth != null && ninth != null) {
        block(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)
    } else {
        null
    }
}
