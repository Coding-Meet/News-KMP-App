package com.coding.meet.newsapp.ui.article_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.utils.shareLink
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArticleDetailRoute(
    articleId: String,
    onNavigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<ArticleDetailViewModel>(parameters = { parametersOf(articleId) })
    val state by viewModel.state.collectAsStateWithLifecycle()
    val openurl = LocalUriHandler.current
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ArticleDetailEffect.Share -> shareLink(effect.url)
                is ArticleDetailEffect.OpenUrl -> {
                    openurl.openUri(effect.url)
                }

                ArticleDetailEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    ArticleDetailScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}
