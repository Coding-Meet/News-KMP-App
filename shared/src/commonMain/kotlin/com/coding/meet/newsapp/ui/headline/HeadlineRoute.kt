package com.coding.meet.newsapp.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HeadlineRoute(
    onArticleClick: (articleId: String) -> Unit,
    onSettingsClick: () -> Unit,
) {
    val viewModel = koinViewModel<HeadlineViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HeadlineEffect.NavigateToDetail -> onArticleClick(effect.articleId)
            }
        }
    }

    HeadlineScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onSettingsClick = onSettingsClick,
    )
}
