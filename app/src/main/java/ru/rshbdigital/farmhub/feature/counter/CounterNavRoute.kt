package ru.rshbdigital.farmhub.feature.counter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navArgument
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.core.navigation.NavRoute

object CounterNavRoute : NavRoute<CounterViewModel> {

    const val DEFAULT_COUNTER_ROUTE = -1

    override val route = Routes.COUNTER_ROUTE

    @Composable
    override fun viewModel(): CounterViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: CounterViewModel) {
        val uiState by viewModel.uiState.collectAsState()
        SomeScreen(
            uiState = uiState,
            onClickIncrease = viewModel::onClickIncrease,
            onClickNext = viewModel::onClickNext,
            onClickRequests = viewModel::onClickRequests
        )
    }

    override fun getArguments() = listOf(
        navArgument(COUNTER_PARAM) { defaultValue = DEFAULT_COUNTER_ROUTE }
    )
}