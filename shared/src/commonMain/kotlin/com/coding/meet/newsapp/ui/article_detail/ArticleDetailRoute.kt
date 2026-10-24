package com.coding.meet.newsapp.ui.article_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.utils.shareLink
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArticleDetailRoute(
    articleId: String,
    onNavigateBack: () -> Unit,
    viewModel: ArticleDetailViewModel = koinViewModel(parameters = { parametersOf(articleId) }),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ArticleDetailEffect.Share -> shareLink(effect.url)
                is ArticleDetailEffect.OpenUrl -> { /* handled in screen via LocalUriHandler */ }
                ArticleDetailEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    ArticleDetailScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}
