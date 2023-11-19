package ru.rshbdigital.farmhub.feature.tasks

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.core.navigation.NavRoute
import ru.rshbdigital.farmhub.core.util.collectAsEffect

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
            secondaryButtonClick = viewModel::secondaryButtonClick,
            refresh = viewModel::refresh
        )
        val context = LocalContext.current
        viewModel.errorMessage.collectAsEffect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}