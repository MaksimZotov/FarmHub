package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.TaskBadgeItem
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens

@Composable
fun TaskBadge(badge: TaskBadgeItem) {
    Surface(
        border = BorderStroke(DimenTokens.x0_25, badge.strokeColor.get()),
        color = badge.backgroundColor.get(),
        shape = RoundedCornerShape(DimenTokens.x2),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = DimenTokens.x4, vertical = DimenTokens.x1),
            text = badge.text.getString(),
            color = badge.textColor.get(),
            style = Typography.badge,
        )
    }
}

@Composable
private fun TaskBadgePreview(badge: TaskBadgeItem) {
    FarmHubTheme {
        TaskBadge(
            badge = badge,
        )
    }
}

@Preview
@Composable
private fun PendingHighPriorityBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.PendingHighPriorityBadge)
}

@Preview
@Composable
private fun PendingMediumPriorityBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.PendingMediumPriorityBadge)
}

@Preview
@Composable
private fun PendingLowPriorityBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.PendingLowPriorityBadge)
}

@Preview
@Composable
private fun MachineInspectionBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.MachineInspectionBadge)
}

@Preview
@Composable
private fun PausedBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.PausedBadge)
}

@Preview
@Composable
private fun DoneBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.DoneBadge)
}

@Preview
@Composable
private fun ClosedBadgePreview() {
    TaskBadgePreview(badge = TaskBadgeItem.ClosedBadge)
}
