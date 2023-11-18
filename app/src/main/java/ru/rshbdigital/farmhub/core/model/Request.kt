package ru.rshbdigital.farmhub.core.model

sealed interface Request {
    val id: Long
    val errorCode: Int?

    data class SetCounter(
        override val id: Long = 0,
        override val errorCode: Int? = null,
        val count: Int
    ): Request
}
