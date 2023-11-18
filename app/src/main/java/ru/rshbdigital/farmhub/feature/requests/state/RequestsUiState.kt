package ru.rshbdigital.farmhub.feature.requests.state

import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.state.UiState

data class RequestsUiState(
    val requests: List<Request>
) : UiState() {

    companion object {
        fun getInitial() = RequestsUiState(
            requests = emptyList()
        )
    }
}