package ru.rshbdigital.farmhub.feature.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.components.OfflinePopup
import ru.rshbdigital.farmhub.core.ui.components.TaskSnippet
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem
import ru.rshbdigital.farmhub.feature.tasks.state.TasksUiState

@Composable
fun TasksScreen(
    uiState: TasksUiState,
    primaryButtonClick: (TaskSnippetItem) -> Unit,
    secondaryButtonClick: (TaskSnippetItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OfflinePopup()
        }
        items(uiState.tasks) { task ->
            TaskSnippet(
                task = task,
                primaryButtonClick = {
                    primaryButtonClick(task)
                },
                secondaryButtonClick = {
                    secondaryButtonClick(task)
                }
            )
        }
    }
}

@Preview
@Composable
fun SomeScreenPreview() {
    FarmHubTheme {
        TasksScreen(
            uiState = TasksUiState.getInitial(),
            primaryButtonClick = {},
            secondaryButtonClick = {}
        )
    }
}