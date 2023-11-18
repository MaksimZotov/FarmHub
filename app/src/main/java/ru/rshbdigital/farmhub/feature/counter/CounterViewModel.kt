package ru.rshbdigital.farmhub.feature.counter

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.rshbdigital.farmhub.client.counter.CounterRepository
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator
import ru.rshbdigital.farmhub.core.routes.COUNTER_PARAM
import ru.rshbdigital.farmhub.core.routes.Routes
import ru.rshbdigital.farmhub.core.state.BaseViewModel
import ru.rshbdigital.farmhub.feature.counter.state.CounterUiState
import ru.rshbdigital.farmhub.feature.counter.state.CounterVmState
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
    private val counterRepository: CounterRepository
) : BaseViewModel<CounterVmState, CounterUiState>(routeNavigator) {

    private val count = savedStateHandle.get<Int>(COUNTER_PARAM)
        ?: CounterNavRoute.DEFAULT_COUNTER_ROUTE

    override fun getInitialVmState() = CounterVmState.getInitial()

    override fun getInitialUiState() = CounterUiState.getInitial()

    override fun mapVmStateToUiState(vmState: CounterVmState) = CounterUiState(
        someStableCount = vmState.someUnstableCount,
        isProgress = vmState.isProgress
    )

    init {
        launchSafe {
            val counter = counterRepository.getCounter(count)
            updateState {
                it.copy(
                    someUnstableCount = counter.count,
                    isProgress = false
                )
            }
        }
    }

    fun onClickIncrease() = launchSafe {
        val newCount = state.someUnstableCount + 1
        updateState {
            it.copy(
                isProgress = true
            )
        }
        val counter = counterRepository.setCounter(newCount)
        updateState {
            it.copy(
                someUnstableCount = counter.count,
                isProgress = false
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

    override fun handleError(exception: Exception?) {
        updateState {
            it.copy(
                isProgress = false,
                errorMessage = exception?.message
            )
        }
    }

    fun onClickRequests() {
        routeNavigator.navigateToRoute(
            route = Routes.REQUESTS_ROUTE
        )
    }
}