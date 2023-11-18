package ru.rshbdigital.farmhub.core.routes

object Routes {
    const val TAB_TASKS_ROUTE = "tab_tasks"
    const val TAB_REPORTS_ROUTE = "tab_reports"
    const val TAB_PROFILE_ROUTE = "tab_profile"
    const val COUNTER_ROUTE = "some_path?$COUNTER_PARAM={$COUNTER_PARAM}"
    const val REQUESTS_ROUTE = "requests"
    const val TASKS_ROUTE = "tasks"

    fun getCounterRoute(counterParam: Int) = COUNTER_ROUTE.replace(
        "{$COUNTER_PARAM}",
        "$counterParam"
    )
}