package ru.rshbdigital.farmhub.feature.tasks.state

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.state.UiState
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem

data class TasksUiState(
    val tasks: ImmutableList<TaskSnippetItem>,
    val isProgress: Boolean
) : UiState() {

    companion object {
        fun getInitial() = TasksUiState(
            tasks = persistentListOf(),
            isProgress = false
        )
    }
}