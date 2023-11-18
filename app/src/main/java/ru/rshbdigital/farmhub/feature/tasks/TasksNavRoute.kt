package ru.rshbdigital.farmhub.feature.tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.core.navigation.NavRoute

object TasksNavRoute : NavRoute<TasksViewModel> {

    override val route = Routes.TASKS_ROUTE

    @Composable
    override fun viewModel(): TasksViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: TasksViewModel) {
        val uiState by viewModel.uiState.collectAsState()
        TasksScreen(
            uiState = uiState,
            primaryButtonClick = viewModel::primaryButtonClick,
            secondaryButtonClick = viewModel::secondaryButtonClick
        )
    }
}