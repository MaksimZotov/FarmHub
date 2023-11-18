package ru.rshbdigital.farmhub.feature.counter.state

import ru.rshbdigital.farmhub.core.state.VmState

data class CounterVmState(
    val someUnstableCount: Int,
    val isProgress: Boolean,
    val errorMessage: String?
) : VmState() {

    companion object {
        fun getInitial() = CounterVmState(
            someUnstableCount = 0,
            isProgress = true,
            errorMessage = null
        )
    }
}