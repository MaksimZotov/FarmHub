package ru.rshbdigital.farmhub.core.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class AlertDialogItem(
    val title: Text,
    val message: Text? = null,
    val primaryButtonText: Text? = null,
    val secondaryButtonText: Text? = null,
    val isDismissable: Boolean,
)
