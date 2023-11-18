package ru.rshbdigital.farmhub.main.navigation

sealed interface NavAction {
    object NavigateUp : NavAction
    data class NavigateToRoute(val route: String) : NavAction
    data class PopToRoute(val route: String) : NavAction
}