package ru.rshbdigital.farmhub.feature.requests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.core.navigation.NavRoute

object RequestsNavRoute : NavRoute<RequestsViewModel> {

    override val route = Routes.REQUESTS_ROUTE

    @Composable
    override fun viewModel(): RequestsViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RequestsViewModel) {
        val uiState by viewModel.uiState.collectAsState()
        RequestsScreen(
            uiState = uiState,
            onClickSendAll = viewModel::onClickSendAll
        )
    }
}