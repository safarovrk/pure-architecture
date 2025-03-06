package com.example.feature.transfer.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.models.presentation.ScreenState
import com.example.feature.transfer.presentation.TransferViewModel

@Composable
fun TransferScreen(viewModel: TransferViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is ScreenState.Error -> Text(
                text = currentState.error,
                modifier = Modifier.fillMaxSize()
            )

            ScreenState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is ScreenState.Success -> TransferValueScreen(
                state = currentState.data,
                onEvent = viewModel::onEvent
            )
        }
    }
}
