package ru.rshbdigital.farmhub.core.routes

object Routes {
    private const val SOME_PATH = "some_path?$SOME_PARAM={$SOME_PARAM}"

    fun routeSomePath(someParam: Any) = SOME_PATH.replace(
        "{$SOME_PARAM}",
        "$someParam"
    )
}