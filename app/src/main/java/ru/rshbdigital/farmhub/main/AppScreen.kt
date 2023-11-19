package ru.rshbdigital.farmhub.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.model.User
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.feature.counter.CounterNavRoute
import ru.rshbdigital.farmhub.feature.requests.RequestsNavRoute
import ru.rshbdigital.farmhub.feature.tasks.TasksNavRoute
import ru.rshbdigital.farmhub.main.theme.DimenTokens

enum class Tab(
    @StringRes val tabNameStringId: Int,
    @DrawableRes val tabIcon: Int,
    @DrawableRes val tabIconSelected: Int = tabIcon,
    val route: String,
) {
    TASKS(
        tabNameStringId = R.string.tasks,
        tabIcon = R.drawable.ic_task_list_24,
        tabIconSelected = R.drawable.ic_task_list_filled_24,
        route = Routes.TAB_TASKS_ROUTE
    ),
    REPORTS(
        tabNameStringId = R.string.reports,
        tabIcon = R.drawable.ic_reports_24,
        tabIconSelected = R.drawable.ic_reports_filled_24,
        route = Routes.TAB_REPORTS_ROUTE
    ),
    PROFILE(
        tabNameStringId = R.string.profile,
        tabIcon = R.drawable.ic_profile_24,
        tabIconSelected = R.drawable.ic_profile_filled_24,
        route = Routes.TAB_PROFILE_ROUTE
    )
}
enum class TabsWrapper(val tabs: List<Tab>) {
    AGRICULTURIST(
        listOf(
            Tab.TASKS,
            Tab.REPORTS,
            Tab.PROFILE
        )
    ),
    MECHANIZER(
        listOf(
            Tab.TASKS,
            Tab.PROFILE
        )
    ),
    UNAUTHORIZED(
        emptyList()
    )
}

@Composable
fun AppScreen(
    userRole: User.Role
) {
    val navController = rememberNavController()
    val tabsWrapper = when (userRole) {
        User.Role.AGRICULTURIST -> TabsWrapper.AGRICULTURIST
        User.Role.MECHANIZER -> TabsWrapper.MECHANIZER
        else -> TabsWrapper.UNAUTHORIZED
    }
    val tabs = tabsWrapper.tabs
    FarmHubTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                bottomBar = {
                    Surface(shadowElevation = DimenTokens.x4) {
                        NavigationBar(
                            containerColor = FarmHubTheme.surface.get(),
                        ) {
                            tabs.forEach { tab ->
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                val isSelected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(indicatorColor = FarmHubTheme.tabSelectedColor.get()),
                                    icon = {
                                        val iconResId = if (isSelected) tab.tabIconSelected else tab.tabIcon
                                        Icon(imageVector = ImageVector.vectorResource(id = iconResId), contentDescription = null)
                                    },
                                    selected = isSelected,
                                    label = {
                                        Text(
                                            text = stringResource(tab.tabNameStringId),
                                            style = Typography.bottomNavigation
                                        )
                                    },
                                    onClick = {
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                when (tabsWrapper) {
                    TabsWrapper.AGRICULTURIST -> NavHost(
                        navController = navController,
                        startDestination = Tab.TASKS.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        navigation(
                            route = Tab.TASKS.route,
                            startDestination = Routes.TASKS_ROUTE
                        ) {
                            TasksNavRoute.composable(this, navController)
                        }
                        navigation(
                            route = Tab.REPORTS.route,
                            startDestination = Routes.COUNTER_ROUTE
                        ) {
                            CounterNavRoute.composable(
                                builder = this,
                                navController = navController,
                                customNavArguments = listOf(
                                    navArgument(COUNTER_PARAM) { defaultValue = 0 }
                                )
                            )
                        }
                        navigation(
                            route = Tab.PROFILE.route,
                            startDestination = Routes.REQUESTS_ROUTE
                        ) {
                            RequestsNavRoute.composable(this, navController)
                        }
                    }
                    TabsWrapper.MECHANIZER -> NavHost(
                        navController = navController,
                        startDestination = Tab.TASKS.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        navigation(
                            route = Tab.TASKS.route,
                            startDestination = Routes.TASKS_ROUTE
                        ) {
                            TasksNavRoute.composable(this, navController)
                        }
                        navigation(
                            route = Tab.PROFILE.route,
                            startDestination = Routes.COUNTER_ROUTE
                        ) {
                            CounterNavRoute.composable(
                                builder = this,
                                navController = navController,
                                customNavArguments = listOf(
                                    navArgument(COUNTER_PARAM) { defaultValue = 0 }
                                )
                            )
                            RequestsNavRoute.composable(this, navController)
                        }
                    }
                    TabsWrapper.UNAUTHORIZED -> NavHost(
                        navController = navController,
                        startDestination = Routes.REQUESTS_ROUTE,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        RequestsNavRoute.composable(this, navController)
                    }
                }
            }
        }
    }
}
