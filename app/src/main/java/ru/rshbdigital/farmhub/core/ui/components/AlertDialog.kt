package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.AlertDialogItem
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import ru.rshbdigital.farmhub.core.ui.model.Text as TextItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(
    dialog: AlertDialogItem,
    primaryButtonClick: (() -> Unit)? = null,
    secondaryButtonClick: (() -> Unit)? = null,
) {
    androidx.compose.material3.AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = dialog.isDismissable,
            dismissOnClickOutside = dialog.isDismissable,
        ),
        onDismissRequest = {},
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = FarmHubTheme.surface.get(),
            shape = RoundedCornerShape(DimenTokens.x6),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DimenTokens.x6)
            ) {
                Text(
                    text = dialog.title.getString(),
                    style = Typography.h2,
                    color = FarmHubTheme.onSurface.get(),
                )
                if (dialog.message != null) {
                    Spacer(modifier = Modifier.height(DimenTokens.x3))
                    Text(
                        text = dialog.message.getString(),
                        style = Typography.body1,
                        color = FarmHubTheme.primary.get().copy(alpha = 0.6f),
                    )
                }
                if (dialog.primaryButtonText != null && primaryButtonClick != null ||
                    dialog.secondaryButtonText != null && secondaryButtonClick != null
                ) {
                    Spacer(modifier = Modifier.height(DimenTokens.x8))
                }
                if (dialog.primaryButtonText != null && primaryButtonClick != null) {
                    ActionButton(
                        text = dialog.primaryButtonText,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = FarmHubTheme.primary.get(),
                            contentColor = FarmHubTheme.onPrimary.get(),
                        ),
                        onClick = primaryButtonClick,
                        minHeight = DimenTokens.x12,
                    )
                }
                if (dialog.primaryButtonText != null && primaryButtonClick != null &&
                    dialog.secondaryButtonText != null && secondaryButtonClick != null
                ) {
                    Spacer(modifier = Modifier.height(DimenTokens.x2))
                }
                if (dialog.secondaryButtonText != null && secondaryButtonClick != null) {
                    ActionButton(
                        text = dialog.secondaryButtonText,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = FarmHubTheme.secondary.get(),
                            contentColor = FarmHubTheme.onSecondary.get(),
                        ),
                        onClick = secondaryButtonClick,
                        minHeight = DimenTokens.x12,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AlertDialogWithoutButtonsPreview() {
    AlertDialog(
        dialog = AlertDialogItem(
            title = TextItem.Simple("Авторизация"),
            message = TextItem.Simple("Для начала работы поднесите RFID-карту к устройству"),
            isDismissable = false,
        )
    )
}

@Preview
@Composable
private fun AlertDialogWithButtonsPreview() {
    AlertDialog(
        dialog = AlertDialogItem(
            title = TextItem.Simple("Вы уверены, что хотите завершить задачу?"),
            primaryButtonText = TextItem.Simple("Завершить"),
            secondaryButtonText = TextItem.Simple("Вернуться к задачам"),
            isDismissable = true,
        ),
        primaryButtonClick = {},
        secondaryButtonClick = {},
    )
}

@Preview
@Composable
private fun AlertDialogWithPrimaryButtonPreview() {
    AlertDialog(
        dialog = AlertDialogItem(
            title = TextItem.Simple("Плохое соединение"),
            message = TextItem.Simple("Сообщение будет доставлено как только появится сеть"),
            primaryButtonText = TextItem.Simple("Завершить"),
            isDismissable = true,
        ),
        primaryButtonClick = {},
    )
}

@Preview
@Composable
private fun AlertDialogWithSecondaryButtonPreview() {
    AlertDialog(
        dialog = AlertDialogItem(
            title = TextItem.Simple("Задача завершена"),
            secondaryButtonText = TextItem.Simple("Вернуться к задачам"),
            isDismissable = true,
        ),
        secondaryButtonClick = {},
    )
}
