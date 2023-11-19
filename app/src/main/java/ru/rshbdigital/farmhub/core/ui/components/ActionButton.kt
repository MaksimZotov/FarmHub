package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.ui.model.getString
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import ru.rshbdigital.farmhub.core.ui.model.Text as TextItem

@Composable
fun ActionButton(
    text: TextItem,
    colors: ButtonColors,
    minHeight: Dp,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = minHeight),
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
