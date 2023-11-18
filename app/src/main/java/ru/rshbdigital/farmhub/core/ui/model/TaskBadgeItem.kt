package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.ColorContainer
import ru.rshbdigital.farmhub.core.design.FarmHubColors

@Immutable
class TaskBadgeItem private constructor(
    val strokeColor: ColorContainer,
    val backgroundColor: ColorContainer,
    val textColor: ColorContainer,
    val text: Text,
) {
    companion object {
        val PendingHighPriorityBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.pendingBadgeHighPriority,
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            textColor = TaskBadgeColors.pendingBadgeHighPriority,
            text = Text.Resource(R.string.status_pending),
        )

        val PendingMediumPriorityBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.pendingBadgeMediumPriority,
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            textColor = TaskBadgeColors.pendingBadgeMediumPriority,
            text = Text.Resource(R.string.status_pending),
        )

        val PendingLowPriorityBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.pendingBadgeLowPriority,
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            textColor = TaskBadgeColors.pendingBadgeLowPriority,
            text = Text.Resource(R.string.status_pending),
        )

        val DoneBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.doneBadgeStroke,
            backgroundColor = TaskBadgeColors.doneBadgeBackground,
            textColor = TaskBadgeColors.doneBadgeText,
            text = Text.Resource(R.string.status_done),
        )

        val ClosedBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.doneBadgeStroke,
            backgroundColor = TaskBadgeColors.doneBadgeBackground,
            textColor = TaskBadgeColors.doneBadgeText,
            text = Text.Resource(R.string.status_closed),
        )

        val PausedBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.pausedBadgeStroke,
            backgroundColor = TaskBadgeColors.pausedBadgeBackground,
            textColor = TaskBadgeColors.pausedBadgeText,
            text = Text.Resource(R.string.status_paused),
        )

        val MachineInspectionBadge = TaskBadgeItem(
            strokeColor = TaskBadgeColors.inspectionBadgeStroke,
            backgroundColor = TaskBadgeColors.inspectionBadgeBackground,
            textColor = TaskBadgeColors.inspectionBadgeText,
            text = Text.Resource(R.string.status_machine_inspection),
        )
    }
}

object TaskBadgeColors {
    val pendingBadgeBackground = ColorContainer(
        light = FarmHubColors.White,
    )
    val pendingBadgeHighPriority = ColorContainer(
        light = FarmHubColors.CongoPink,
    )
    val pendingBadgeMediumPriority = ColorContainer(
        light = FarmHubColors.Rajah,
    )
    val pendingBadgeLowPriority = ColorContainer(
        light = FarmHubColors.NaplesYellow,
    )

    val doneBadgeBackground = ColorContainer(
        light = FarmHubColors.Nyanza,
    )
    val doneBadgeStroke = ColorContainer(
        light = FarmHubColors.Celadon,
    )
    val doneBadgeText = ColorContainer(
        light = FarmHubColors.Axolotl,
    )

    val pausedBadgeBackground = ColorContainer(
        light = FarmHubColors.MistyRose,
    )
    val pausedBadgeStroke = ColorContainer(
        light = FarmHubColors.Melon,
    )
    val pausedBadgeText = ColorContainer(
        light = FarmHubColors.SunsetOrange,
    )

    val inspectionBadgeBackground = ColorContainer(
        light = FarmHubColors.AntiFlashWhite,
    )
    val inspectionBadgeStroke = ColorContainer(
        light = FarmHubColors.ColumbiaBlue,
    )
    val inspectionBadgeText = ColorContainer(
        light = FarmHubColors.MaastrichtBlue,
    )
}
