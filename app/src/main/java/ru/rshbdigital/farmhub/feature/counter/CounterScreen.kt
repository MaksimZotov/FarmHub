package ru.rshbdigital.farmhub.feature.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rshbdigital.farmhub.feature.counter.state.CounterUiState
import ru.rshbdigital.farmhub.main.theme.FarmHubTheme

@Composable
fun SomeScreen(
    uiState: CounterUiState,
    onClickIncrease: () -> Unit,
    onClickNext: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Счётчик: ${uiState.someStableCount}",
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickIncrease
        ) {
            Text(
                text = "Увеличить"
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickNext
        ) {
            Text(
                text = "Дальше x2"
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
            onClickNext = {}
        )
    }
}