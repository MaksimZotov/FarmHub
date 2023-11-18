package ru.rshbdigital.farmhub.feature.requests.state

import ru.rshbdigital.farmhub.core.model.Request
import ru.rshbdigital.farmhub.core.state.VmState

data class RequestsVmState(
    val requests: List<Request>
) : VmState() {

    companion object {
        fun getInitial() = RequestsVmState(
            requests = emptyList()
        )
    }
}