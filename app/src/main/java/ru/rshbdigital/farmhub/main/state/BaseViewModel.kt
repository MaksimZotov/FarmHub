package ru.rshbdigital.farmhub.main.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.rshbdigital.farmhub.main.navigation.RouteNavigator

abstract class BaseViewModel<VM: VmState, UI: UiState>(
    protected val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    abstract fun getInitialVmState(): VM
    abstract fun getInitialUiState(): UI
    abstract fun mapVmStateToUiState(vmState: VM): UI

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
}