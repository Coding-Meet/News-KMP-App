package com.coding.meet.newsapp.ui.screen.bookmark

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.ui.components.ArticleListScreen
import com.coding.meet.newsapp.ui.components.ShimmerEffect
import com.coding.meet.newsapp.ui.designsystem.component.state.NewsEmptyView
import com.coding.meet.newsapp.ui.designsystem.component.state.NewsErrorView
import com.coding.meet.newsapp.ui.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookmarkScreen(
    onArticleClick: (article: Article) -> Unit,
) {
    val viewModel = koinViewModel<BookmarkViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is BookmarkEffect.NavigateToDetail -> onArticleClick(effect.article)
            }
        }
    }

    BookmarkScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}


@Composable
fun BookmarkScreenContent(
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(title = stringResource(NavigationItem.Bookmark.title))
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when {
                state.isLoading -> ShimmerEffect()
                state.error != null -> NewsErrorView(
                    message = state.error,
                    icon = Icons.Outlined.CloudOff,
                )

                state.articles.isEmpty() -> NewsEmptyView(
                    title = stringResource(Res.string.no_bookmarks),
                    icon = Icons.Outlined.BookmarkBorder,
                )

                else -> ArticleListScreen(
                    articleList = state.articles,
                    onArticleClick = { article -> onEvent(BookmarkEvent.OpenArticle(article)) },
                )
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun BookmarkScreenContentPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        BookmarkScreenContent(
            state = BookmarkState(articles = previewArticles),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun BookmarkScreenContentEmptyPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        BookmarkScreenContent(
            state = BookmarkState(articles = emptyList()),
            onEvent = {},
        )
    }
}
