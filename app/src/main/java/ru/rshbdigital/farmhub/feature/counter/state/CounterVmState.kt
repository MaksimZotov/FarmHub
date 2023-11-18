package ru.rshbdigital.farmhub.feature.counter.state

import ru.rshbdigital.farmhub.main.state.VmState

data class CounterVmState(
    val someUnstableCount: Int
) : VmState() {

    companion object {
        fun getInitial() = CounterVmState(
            someUnstableCount = 0
        )
    }
}