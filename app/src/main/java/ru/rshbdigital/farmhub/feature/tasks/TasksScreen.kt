package ru.rshbdigital.farmhub.feature.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.components.ChipsBlock
import ru.rshbdigital.farmhub.core.ui.components.MainToolbar
import ru.rshbdigital.farmhub.core.ui.components.OfflinePopup
import ru.rshbdigital.farmhub.core.ui.components.TaskSnippet
import ru.rshbdigital.farmhub.core.ui.model.ChipItem
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem
import ru.rshbdigital.farmhub.core.ui.model.Text
import ru.rshbdigital.farmhub.core.ui.model.ToolbarItem
import ru.rshbdigital.farmhub.feature.tasks.state.TasksUiState
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatCurrentDayToString(): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
}

val date = formatCurrentDayToString()

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun TasksScreen(
    uiState: TasksUiState,
    primaryButtonClick: (TaskSnippetItem) -> Unit,
    secondaryButtonClick: (TaskSnippetItem) -> Unit,
    refresh: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = refresh
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FarmHubTheme.background.get())
        ) {
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                var selectedChip by remember { mutableStateOf(1) }
                LazyColumn(
                ) {
                    item {
                        Column {
                            MainToolbar(
                                item = ToolbarItem(
                                    title = Text.Simple("Мои задачи"),
                                    date = Text.Simple("Сегодня, $date"),
                                )
                            )
                            ChipsBlock(
                                chips = persistentListOf(
                                    ChipItem(
                                        id = 1,
                                        text = Text.Simple("Текущие"),
                                        isSelected = selectedChip == 1
                                    ),
                                    ChipItem(
                                        id = 2,
                                        text = Text.Simple("Планируемые"),
                                        isSelected = selectedChip == 2
                                    ),
                                    ChipItem(
                                        id = 3,
                                        text = Text.Simple("Завершённые"),
                                        isSelected = selectedChip == 3
                                    ),
                                ),
                                onChipClicked = { index -> selectedChip = index }
                            )
                        }
                    }
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
                        Spacer(modifier = Modifier.height(DimenTokens.x4))
                    }
                }
            }
        }
        PullRefreshIndicator(
            uiState.isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun SomeScreenPreview() {
    FarmHubTheme {
        TasksScreen(
            uiState = TasksUiState.getInitial(),
            primaryButtonClick = {},
            secondaryButtonClick = {},
            refresh = {}
        )
    }
}
