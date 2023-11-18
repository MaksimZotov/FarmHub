package ru.rshbdigital.farmhub.model

sealed class Operation {
    abstract val id: String
    abstract val name: String
    abstract val speed: Float

    data class Seeding(
        override val id: String,
        override val name: String,
        val depth: Float,
        override val speed: Float,
    ) : Operation()

    data class Protection(
        override val id: String,
        override val name: String,
        val flowRate: Float,
        override val speed: Float,
    ) : Operation()

    data class Harvesting(
        override val id: String,
        override val name: String,
        val depth: Float,
        override val speed: Float,
    ) : Operation()

    data class SoilPreparation(
        override val id: String,
        override val name: String,
        val depth: Float,
        override val speed: Float,
    ) : Operation()
}
