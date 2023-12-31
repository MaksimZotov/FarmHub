package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.model.ChipItem
import ru.rshbdigital.farmhub.core.ui.model.Text
import ru.rshbdigital.farmhub.main.theme.DimenTokens

@Composable
fun ChipsBlock(
    chips: List<ChipItem>,
    onChipClicked: (index: Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DimenTokens.x2),
        contentPadding = PaddingValues(horizontal = DimenTokens.x4, vertical = DimenTokens.x2),
    ) {
        items(items = chips) { chip ->
            TaskFilterChip(chip = chip, onChipClicked = { onChipClicked(chip.id) })
        }
    }
}

@Preview(backgroundColor = 0xFFEEEFF3, showBackground = true)
@Composable
private fun ChipsBlockPreview() {
    FarmHubTheme {
        ChipsBlock(
            chips = listOf(
                ChipItem(
                    id = 1,
                    text = Text.Simple("Текущие"),
                    isSelected = false,
                ),
                ChipItem(
                    id = 2,
                    text = Text.Simple("Планируемые"),
                    isSelected = true,
                ),
                ChipItem(
                    id = 3,
                    text = Text.Simple("Завершенные"),
                    isSelected = false,
                )
            ),
            onChipClicked = {},
        )
    }
}
