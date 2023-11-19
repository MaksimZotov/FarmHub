package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.ToolbarItem
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import ru.rshbdigital.farmhub.core.ui.model.Text as TextItem

@Composable
fun MainToolbar(item: ToolbarItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DimenTokens.x4),
    ) {
        Text(
            modifier = Modifier.alignByBaseline(),
            text = item.title.getString(),
            style = Typography.h3,
            color = FarmHubTheme.onSurface.get(),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.alignByBaseline(),
            text = item.date.getString(),
            style = Typography.body2.copy(fontWeight = FontWeight.Medium),
            color = FarmHubTheme.taskSnippetSecondaryInfo.get(),
        )
    }
}

@Preview
@Composable
private fun MainToolbarPreview() {
    FarmHubTheme {
        MainToolbar(
            item = ToolbarItem(
                title = TextItem.Simple("Мои задачи"),
                date = TextItem.Simple("Сегодня, 19.11.23"),
            )
        )
    }
}
