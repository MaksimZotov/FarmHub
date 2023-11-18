package ru.rshbdigital.farmhub.core.routes

object Routes {
    const val COUNTER_ROUTE = "some_path?$COUNTER_PARAM={$COUNTER_PARAM}"

    fun getCounterRoute(counterParam: Int) = COUNTER_ROUTE.replace(
        "{$COUNTER_PARAM}",
        "$counterParam"
    )
}