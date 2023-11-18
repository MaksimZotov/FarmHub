package ru.rshbdigital.farmhub.feature.requests

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.rshbdigital.farmhub.client.offline.OfflineRepository
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator
import ru.rshbdigital.farmhub.core.state.BaseViewModel
import ru.rshbdigital.farmhub.feature.requests.state.RequestsUiState
import ru.rshbdigital.farmhub.feature.requests.state.RequestsVmState
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    private val offlineRepository: OfflineRepository
) : BaseViewModel<RequestsVmState, RequestsUiState>(routeNavigator) {

    override fun getInitialVmState() = RequestsVmState.getInitial()

    override fun getInitialUiState() = RequestsUiState.getInitial()

    override fun mapVmStateToUiState(vmState: RequestsVmState) = RequestsUiState(
        requests = vmState.requests
    )

    init {
        viewModelScope.launch {
            offlineRepository.getAllRequestsFlow().collectLatest { requests ->
                updateState {
                    it.copy(
                        requests = requests
                    )
                }
            }
        }
    }

    fun onClickSendAll() = launchSafe {
        launchSafe {
            offlineRepository.trySendAllRequests()
        }
    }
}