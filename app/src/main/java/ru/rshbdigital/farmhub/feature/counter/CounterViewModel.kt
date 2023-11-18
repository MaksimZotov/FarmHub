package ru.rshbdigital.farmhub.feature.counter

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.feature.counter.state.CounterUiState
import ru.rshbdigital.farmhub.feature.counter.state.CounterVmState
import ru.rshbdigital.farmhub.main.navigation.RouteNavigator
import ru.rshbdigital.farmhub.main.state.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CounterVmState, CounterUiState>(routeNavigator) {

    private val counter = savedStateHandle.get<Int>(COUNTER_PARAM)
        ?: CounterNavRoute.DEFAULT_COUNTER_ROUTE

    override fun getInitialVmState() = CounterVmState.getInitial().copy(
        someUnstableCount = counter
    )

    override fun getInitialUiState() = CounterUiState.getInitial().copy(
        someStableCount = counter
    )

    override fun mapVmStateToUiState(vmState: CounterVmState) = CounterUiState(
        someStableCount = vmState.someUnstableCount
    )

    fun onClickIncrease() {
        updateState {
            it.copy(
                someUnstableCount = it.someUnstableCount + 1
            )
        }
    }

    fun onClickNext() {
        routeNavigator.navigateToRoute(
            route = Routes.getCounterRoute(
                counterParam = state.someUnstableCount * 2
            )
        )
    }
}