package ru.rshbdigital.farmhub.core.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator

abstract class BaseViewModel<VM: VmState, UI: UiState>(
    protected val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    abstract fun getInitialVmState(): VM
    abstract fun getInitialUiState(): UI
    abstract fun mapVmStateToUiState(vmState: VM): UI

    private var job: Job? = null

    private val vmState by lazy {
        MutableStateFlow(getInitialVmState())
    }

    val uiState by lazy {
        vmState.map(::mapVmStateToUiState).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = getInitialUiState()
        )
    }

    protected val state get() = vmState.value

    protected fun updateState(copy: (VM) -> VM) {
        vmState.value = copy(state)
    }

    protected fun launchSafe(action: suspend () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                action()
            } catch (exception: Exception) {
                if (exception !is CancellationException) {
                    handleError(exception)
                }
            }
        }
    }

    protected open fun handleError(exception: Exception?) {

    }
}