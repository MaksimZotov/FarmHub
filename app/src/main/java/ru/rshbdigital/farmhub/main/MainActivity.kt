package ru.rshbdigital.farmhub.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.feature.counter.CounterNavRoute
import ru.rshbdigital.farmhub.main.theme.FarmHubTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FarmHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
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
                }
            }
        }
    }
}