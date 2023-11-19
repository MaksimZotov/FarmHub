package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChipItem(
    val text: Text,
    val isSelected: Boolean,
)
