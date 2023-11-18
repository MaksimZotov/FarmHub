package ru.rshbdigital.farmhub.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.rshbdigital.farmhub.core.util.collectAsEffect

interface NavRoute<T : RouteNavigator> {

    val route: String

    @Composable
    fun Content(viewModel: T)

    @Composable
    fun viewModel(): T

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun composable(
        builder: NavGraphBuilder,
        navController: NavHostController,
        customNavArguments: List<NamedNavArgument>? = null
    ) {
        builder.composable(route, customNavArguments ?: getArguments()) {
            val viewModel = viewModel()
            viewModel.navAction.collectAsEffect { navAction ->
                sendNavAction(
                    navController = navController,
                    navAction = navAction
                )
            }
            Content(viewModel)
        }
    }


    private fun sendNavAction(
        navController: NavHostController,
        navAction: NavAction
    ) {
        when (navAction) {
            is NavAction.NavigateToRoute -> {
                navController.navigate(navAction.route)
            }
            is NavAction.PopToRoute -> {
                navController.popBackStack(navAction.route, false)
            }
            is NavAction.NavigateUp -> {
                navController.navigateUp()
            }
        }
    }
}