package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.ColorContainer
import ru.rshbdigital.farmhub.core.design.FarmHubColors

@Immutable
class TaskBadgeItem private constructor(
    val backgroundColor: ColorContainer,
    val strokeColor: ColorContainer = backgroundColor,
    val textColor: ColorContainer,
    val text: Text,
) {
    companion object {
        @Stable
        val PendingHighPriorityBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            strokeColor = TaskBadgeColors.pendingBadgeHighPriority,
            textColor = TaskBadgeColors.pendingBadgeHighPriority,
            text = Text.Resource(R.string.status_pending),
        )

        @Stable
        val PendingMediumPriorityBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            strokeColor = TaskBadgeColors.pendingBadgeMediumPriority,
            textColor = TaskBadgeColors.pendingBadgeMediumPriority,
            text = Text.Resource(R.string.status_pending),
        )

        @Stable
        val PendingLowPriorityBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            strokeColor = TaskBadgeColors.pendingBadgeLowPriority,
            textColor = TaskBadgeColors.pendingBadgeLowPriority,
            text = Text.Resource(R.string.status_pending),
        )

        @Stable
        val InProgressBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.pendingBadgeBackground,
            strokeColor = TaskBadgeColors.pendingBadgeHighPriority,
            textColor = TaskBadgeColors.pendingBadgeHighPriority,
            text = Text.Resource(R.string.status_in_progress),
        )

        @Stable
        val DoneBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.doneBadgeBackground,
            textColor = TaskBadgeColors.doneBadgeText,
            text = Text.Resource(R.string.status_done),
        )

        @Stable
        val ClosedBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.doneBadgeBackground,
            textColor = TaskBadgeColors.doneBadgeText,
            text = Text.Resource(R.string.status_closed),
        )

        @Stable
        val PausedBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.pausedBadgeBackground,
            textColor = TaskBadgeColors.pausedBadgeText,
            text = Text.Resource(R.string.status_paused),
        )

        @Stable
        val MachineInspectionBadge = TaskBadgeItem(
            backgroundColor = TaskBadgeColors.inspectionBadgeBackground,
            textColor = TaskBadgeColors.inspectionBadgeText,
            text = Text.Resource(R.string.status_machine_inspection),
        )
    }
}

object TaskBadgeColors {
    @Stable
    val pendingBadgeBackground = ColorContainer(
        light = FarmHubColors.White,
    )

    @Stable
    val pendingBadgeHighPriority = ColorContainer(
        light = FarmHubColors.CongoPink,
    )

    @Stable
    val pendingBadgeMediumPriority = ColorContainer(
        light = FarmHubColors.Rajah,
    )

    @Stable
    val pendingBadgeLowPriority = ColorContainer(
        light = FarmHubColors.NaplesYellow,
    )

    @Stable
    val doneBadgeBackground = ColorContainer(
        light = FarmHubColors.Nyanza,
    )

    @Stable
    val doneBadgeText = ColorContainer(
        light = FarmHubColors.Axolotl,
    )

    @Stable
    val pausedBadgeBackground = ColorContainer(
        light = FarmHubColors.MistyRose,
    )

    @Stable
    val pausedBadgeText = ColorContainer(
        light = FarmHubColors.SunsetOrange,
    )

    @Stable
    val inspectionBadgeBackground = ColorContainer(
        light = FarmHubColors.AntiFlashWhite,
    )

    @Stable
    val inspectionBadgeText = ColorContainer(
        light = FarmHubColors.MaastrichtBlue,
    )
}
