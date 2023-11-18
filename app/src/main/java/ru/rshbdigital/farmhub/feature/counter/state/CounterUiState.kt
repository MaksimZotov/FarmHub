package ru.rshbdigital.farmhub.feature.counter.state

import ru.rshbdigital.farmhub.main.state.UiState

data class CounterUiState(
    val someStableCount: Int
) : UiState() {

    companion object {
        fun getInitial() = CounterUiState(
            someStableCount = 0
        )
    }
}