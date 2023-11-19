package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChipItem(
    val id: Int,
    val text: Text,
    val isSelected: Boolean,
)
