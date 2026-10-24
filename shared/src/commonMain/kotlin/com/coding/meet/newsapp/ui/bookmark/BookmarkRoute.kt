package com.coding.meet.newsapp.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookmarkRoute(
    onArticleClick: (articleId: String) -> Unit,
    viewModel: BookmarkViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is BookmarkEffect.NavigateToDetail -> onArticleClick(effect.articleId)
            }
        }
    }

    BookmarkScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}
