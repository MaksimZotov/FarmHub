package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.ChipItem
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import ru.rshbdigital.farmhub.core.ui.model.Text as TextItem

@Composable
fun TaskFilterChip(
    chip: ChipItem,
    onChipClicked: () -> Unit,
) {
    val chipColor = if (chip.isSelected) FarmHubTheme.tabSelectedColor else FarmHubTheme.surface
    val chipTextColor = if (chip.isSelected) FarmHubTheme.onSurface else FarmHubTheme.taskSnippetSecondaryInfo
    Surface(
        color = chipColor.get(),
        shape = RoundedCornerShape(DimenTokens.x2),
        onClick = onChipClicked,
        enabled = chip.id == 1
    ) {
        Text(
            modifier = Modifier.padding(horizontal = DimenTokens.x6, vertical = DimenTokens.x2),
            text = chip.text.getString(),
            color = chipTextColor.get(),
            style = Typography.body2.copy(fontWeight = FontWeight.Medium),
        )
    }
}

@Preview(backgroundColor = 0xFFEEEFF3, showBackground = true)
@Composable
private fun TaskFilterChipPreview() {
    FarmHubTheme {
        TaskFilterChip(
            chip = ChipItem(
                id = 1,
                text = TextItem.Simple("Текущие"),
                isSelected = false,
            ),
            onChipClicked = {},
        )
    }
}


@Preview(backgroundColor = 0xFFEEEFF3, showBackground = true)
@Composable
private fun SelectedTaskFilterChipPreview() {
    FarmHubTheme {
        TaskFilterChip(
            chip = ChipItem(
                id = 2,
                text = TextItem.Simple("Планируемые"),
                isSelected = true,
            ),
            onChipClicked = {},
        )
    }
}
