package ru.rshbdigital.farmhub.main.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

interface RouteNavigator {

    val navAction: Flow<NavAction>

    fun navigateUp()

    fun navigateToRoute(route: String)

    fun popToRoute(route: String)
}

@Singleton
class RouteNavigatorImpl @Inject constructor() : RouteNavigator {

    private val _navAction = MutableSharedFlow<NavAction>(
        extraBufferCapacity = 1
    )

    override val navAction = _navAction.asSharedFlow()

    override fun navigateUp() = navigate(NavAction.NavigateUp)

    override fun navigateToRoute(route: String) = navigate(NavAction.NavigateToRoute(route))

    override fun popToRoute(route: String) = navigate(NavAction.PopToRoute(route))

    private fun navigate(navAction: NavAction) {
        _navAction.tryEmit(navAction)
    }
}