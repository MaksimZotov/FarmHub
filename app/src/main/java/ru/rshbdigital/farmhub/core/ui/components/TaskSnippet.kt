package ru.rshbdigital.farmhub.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.TaskBadgeItem
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem
import ru.rshbdigital.farmhub.core.ui.model.Text
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens

@Composable
fun TaskSnippet(
    task: TaskSnippetItem,
    primaryButtonClick: () -> Unit,
    secondaryButtonClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .run { if (task.isActive.not()) alpha(0.6f) else this },
        shape = RoundedCornerShape(DimenTokens.x6),
        color = FarmHubTheme.surface.get(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DimenTokens.x4),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(DimenTokens.x4),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_16),
                    tint = FarmHubTheme.taskSnippetSecondaryInfo.get(),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(DimenTokens.x1))
                Text(
                    text = task.date.getString(),
                    color = FarmHubTheme.taskSnippetSecondaryInfo.get(),
                    style = Typography.body2.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.width(DimenTokens.x3))
                Text(
                    text = task.time.getString(),
                    color = FarmHubTheme.taskSnippetTimeInfo.get(),
                    style = Typography.body2.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.weight(1f))
                TaskBadge(badge = task.badge)
            }
            Spacer(modifier = Modifier.height(DimenTokens.x2))
            Text(
                text = task.title.getString(),
                color = FarmHubTheme.onSurface.get(),
                style = Typography.h3,
            )
            Spacer(modifier = Modifier.height(DimenTokens.x4))
            TaskDetailItem(R.drawable.ic_location_geo_tag_20, task.location)
            Spacer(modifier = Modifier.height(DimenTokens.x2))
            TaskDetailItem(R.drawable.ic_tractor_20, task.machine)
            Spacer(modifier = Modifier.height(DimenTokens.x2))
            TaskDetailItem(R.drawable.ic_trailing_unit_20, task.trailingUnit)
            Spacer(modifier = Modifier.height(DimenTokens.x2))
            TaskDetailItem(R.drawable.ic_wheat_20, task.plantType)

            task.additionalParams.forEachIndexed { index, param ->
                val spacerHeight = if (index == 0) DimenTokens.x3 else DimenTokens.x1
                Spacer(modifier = Modifier.height(spacerHeight))
                Text(
                    text = param.getString(),
                    color = FarmHubTheme.primary.get().copy(alpha = 0.6f),
                    style = Typography.body1,
                )
            }
            if (task.primaryButtonText != null || task.secondaryButtonText != null) {
                Spacer(modifier = Modifier.height(DimenTokens.x4))
            }
            if (task.primaryButtonText != null) {
                TaskActionButton(
                    text = task.primaryButtonText,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = FarmHubTheme.primary.get(),
                        contentColor = FarmHubTheme.onPrimary.get(),
                    ),
                    onClick = primaryButtonClick,
                )
            }
            if (task.primaryButtonText != null && task.secondaryButtonText != null) {
                Spacer(modifier = Modifier.height(DimenTokens.x2))
            }
            if (task.secondaryButtonText != null) {
                TaskActionButton(
                    text = task.secondaryButtonText,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = FarmHubTheme.secondary.get(),
                        contentColor = FarmHubTheme.onSecondary.get(),
                    ),
                    onClick = secondaryButtonClick,
                )
            }
        }
    }
}

@Composable
private fun TaskDetailItem(@DrawableRes icon: Int, text: Text) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(DimenTokens.x4),
            imageVector = ImageVector.vectorResource(icon),
            tint = FarmHubTheme.onSurface.get(),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(DimenTokens.x1))
        Text(
            text = text.getString(),
            color = FarmHubTheme.onSurface.get(),
            style = Typography.body1,
        )
    }
}

@Composable
private fun TaskActionButton(
    text: Text,
    colors: ButtonColors,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = DimenTokens.x14),
        shape = RoundedCornerShape(DimenTokens.x4),
        colors = colors,
        onClick = onClick,
    ) {
        Text(
            text = text.getString(),
            style = Typography.body1.copy(fontWeight = FontWeight.Medium),
        )
    }

}

@Composable
@Preview(backgroundColor = 0xFFEEEFF3, showBackground = true)
fun TaskSnippetPreview() {
    FarmHubTheme {
        TaskSnippet(
            task = TaskSnippetItem(
                taskId = "17391827389712",
                isActive = true,
                title = Text.Simple("Посев пшеницы"),
                date = Text.Simple("19.11.23"),
                time = Text.Simple("08:00–14:00"),
                badge = TaskBadgeItem.InProgressBadge,
                location = Text.Simple("поле 108"),
                machine = Text.Simple("трактор А512 г/н а123фф"),
                trailingUnit = Text.Simple("ковш 232ф №1239"),
                plantType = Text.Simple("озимая пшеница"),
                additionalParams = listOf(
                    Text.Simple("Скорость: 10–12 км/ч"),
                    Text.Simple("Глубина: 8–10 см"),
                ),
                primaryButtonText = Text.Simple("Завершить работу"),
                secondaryButtonText = Text.Simple("Сообщить о проблеме"),
            ),
            primaryButtonClick = {},
            secondaryButtonClick = {},
        )
    }
}

@Composable
@Preview(backgroundColor = 0xFFEEEFF3, showBackground = true)
fun DisabledTaskSnippetPreview() {
    FarmHubTheme {
        TaskSnippet(
            task = TaskSnippetItem(
                taskId = "17391827389712",
                isActive = false,
                title = Text.Simple("Сбор урожая"),
                date = Text.Simple("19.11.23"),
                time = Text.Simple("08:00–14:00"),
                badge = TaskBadgeItem.DoneBadge,
                location = Text.Simple("поле 108"),
                machine = Text.Simple("трактор А512 г/н а123фф"),
                trailingUnit = Text.Simple("ковш 232ф №1239"),
                plantType = Text.Simple("озимая пшеница"),
                additionalParams = emptyList(),
                primaryButtonText = null,
                secondaryButtonText = null,
            ),
            primaryButtonClick = {},
            secondaryButtonClick = {},
        )
    }
}
