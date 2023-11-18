package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class TaskSnippetItem(
    val taskId: String,
    val isActive: Boolean,
    val title: Text,
    val date: Text,
    val time: Text,
    val badge: TaskBadgeItem,
    val location: Text,
    val machine: Text,
    val trailingUnit: Text,
    val plantType: Text,
    val additionalParams: List<Text>,
    val primaryButtonText: Text?,
    val secondaryButtonText: Text?,
)
