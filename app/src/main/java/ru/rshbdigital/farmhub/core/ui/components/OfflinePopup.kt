package ru.rshbdigital.farmhub.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import ru.rshbdigital.farmhub.core.util.getActivityAs

interface OfflineListener {
    val isOffline: StateFlow<Boolean>
}

@Composable
fun OfflinePopup() {
    val listener = LocalContext.current.getActivityAs<OfflineListener>() ?: return
    val offlinePopup by listener.isOffline.collectAsState()
    if (offlinePopup) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Офлайн",
            fontSize = 20.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}