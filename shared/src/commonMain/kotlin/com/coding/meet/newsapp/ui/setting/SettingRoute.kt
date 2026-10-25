package com.coding.meet.newsapp.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingRoute(
    onNavigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<SettingViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SettingEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    SettingScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}
