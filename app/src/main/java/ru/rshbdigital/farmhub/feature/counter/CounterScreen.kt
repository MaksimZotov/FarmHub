package ru.rshbdigital.farmhub.feature.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.rshbdigital.farmhub.feature.counter.state.CounterUiState
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.components.OfflinePopup

@Composable
fun SomeScreen(
    uiState: CounterUiState,
    onClickIncrease: () -> Unit,
    onClickNext: () -> Unit,
    onClickRequests: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OfflinePopup()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Счётчик: ${uiState.someStableCount}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickIncrease
        ) {
            Text(
                text = "Увеличить",
                fontSize = 20.sp
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickNext
        ) {
            Text(
                text = "Дальше x2",
                fontSize = 20.sp
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickRequests
        ) {
            Text(
                text = "Запросы",
                fontSize = 20.sp
            )
        }
    }
    if (uiState.isProgress) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x807AFF90))
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun SomeScreenPreview() {
    FarmHubTheme {
        SomeScreen(
            uiState = CounterUiState.getInitial(),
            onClickIncrease = {},
            onClickNext = {},
            onClickRequests = {}
        )
    }
}