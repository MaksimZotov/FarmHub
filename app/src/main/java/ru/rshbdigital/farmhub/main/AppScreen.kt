package ru.rshbdigital.farmhub.main

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.model.User
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.feature.counter.CounterNavRoute
import ru.rshbdigital.farmhub.feature.requests.RequestsNavRoute
import ru.rshbdigital.farmhub.feature.tasks.TasksNavRoute

enum class Tab(
    @StringRes val tabNameStringId: Int,
    val route: String
) {
    TASKS(
        tabNameStringId = R.string.tasks,
        route = Routes.TAB_TASKS_ROUTE
    ),
    REPORTS(
        tabNameStringId = R.string.reports,
        route = Routes.TAB_REPORTS_ROUTE
    ),
    PROFILE(
        tabNameStringId = R.string.profile,
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
                    Row {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        tabs.map { tab ->
                            Box(
                                modifier = Modifier
                                    .height(64.dp)
                                    .weight(1f)
                                    .background(
                                        if (currentDestination?.hierarchy?.any { it.route == tab.route } == true) {
                                            Color.Gray
                                        } else {
                                            Color.DarkGray
                                        }
                                    )
                                    .clickable {
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = stringResource(tab.tabNameStringId),
                                    fontSize = 20.sp
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