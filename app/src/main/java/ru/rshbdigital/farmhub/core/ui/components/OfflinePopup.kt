package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import kotlinx.coroutines.flow.StateFlow
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.core.design.FarmHubColors
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.core.util.getActivityAs
import ru.rshbdigital.farmhub.main.theme.DimenTokens

interface OfflineListener {
    val isOffline: StateFlow<Boolean>
}

@Composable
fun OfflinePopup() {
    val listener = LocalContext.current.getActivityAs<OfflineListener>() ?: return
    val offlinePopup by listener.isOffline.collectAsState()
    if (offlinePopup) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color(0xFFEC5B5B))
                .padding(horizontal = DimenTokens.x4, vertical = DimenTokens.x1),
        ) {
            Icon(
                modifier = Modifier.size(DimenTokens.x5),
                imageVector = ImageVector.vectorResource(R.drawable.ic_danger_20),
                tint = FarmHubColors.White,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(DimenTokens.x3))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Нет соединения",
                style = Typography.badge,
                color = FarmHubColors.White,
            )
        }
    }
}
