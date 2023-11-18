package ru.rshbdigital.farmhub.model

sealed class Operation {
    abstract val speed: Float

    data class Seeding(
        val depth: Float,
        override val speed: Float,
    ) : Operation()

    data class Protection(
        val flowRate: Float,
        override val speed: Float,
    ) : Operation()

    data class Harvesting(
        val depth: Float,
        override val speed: Float,
    ) : Operation()

    data class SoilPreparation(
        val depth: Float,
        override val speed: Float,
    ) : Operation()
}
