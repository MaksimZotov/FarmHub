package ru.rshbdigital.farmhub.feature.requests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.offline.OfflinePopup
import ru.rshbdigital.farmhub.feature.requests.state.RequestsUiState

@Composable
fun RequestsScreen(
    uiState: RequestsUiState,
    onClickSendAll: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OfflinePopup()
        }
        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickSendAll
            ) {
                Text(
                    text = "Отправить",
                    fontSize = 20.sp
                )
            }
        }
        items(uiState.requests) { request ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$request",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun SomeScreenPreview() {
    FarmHubTheme {
        RequestsScreen(
            uiState = RequestsUiState.getInitial(),
            onClickSendAll = {}
        )
    }
}