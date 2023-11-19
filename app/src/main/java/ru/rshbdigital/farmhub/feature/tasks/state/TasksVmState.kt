package ru.rshbdigital.farmhub.feature.tasks.state

import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.state.VmState

data class TasksVmState(
    val tasks: List<Task>
) : VmState() {

    companion object {
        fun getInitial() = TasksVmState(
            tasks = emptyList()
        )
    }
}