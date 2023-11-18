package ru.rshbdigital.farmhub.feature.counter.state

import ru.rshbdigital.farmhub.core.state.UiState

data class CounterUiState(
    val someStableCount: Int,
    val isProgress: Boolean
) : UiState() {

    companion object {
        fun getInitial() = CounterUiState(
            someStableCount = 0,
            isProgress = true
        )
    }
}