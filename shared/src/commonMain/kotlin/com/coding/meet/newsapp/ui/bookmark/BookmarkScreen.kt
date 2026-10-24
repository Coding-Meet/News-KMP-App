package com.coding.meet.newsapp.ui.bookmark

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coding.meet.newsapp.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.common.ArticleListScreen
import com.coding.meet.newsapp.ui.common.EmptyContent
import com.coding.meet.newsapp.ui.common.ShimmerEffect
import com.coding.meet.newsapp.utils.navigationItemsLists
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.ic_browse
import news_kmp_app.shared.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(title = stringResource(navigationItemsLists[2].title))
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when {
                state.isLoading -> ShimmerEffect()
                state.error != null -> EmptyContent(
                    message = state.error,
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false,
                )
                state.articles.isEmpty() -> EmptyContent(
                    message = stringResource(Res.string.no_bookmarks),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false,
                )
                else -> ArticleListScreen(
                    articleList = state.articles,
                    onArticleClick = { article -> onEvent(BookmarkEvent.OpenArticle(article.publishedAt)) },
                )
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun BookmarkScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        BookmarkScreen(
            state = BookmarkState(articles = previewArticles),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun BookmarkScreenEmptyPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        BookmarkScreen(
            state = BookmarkState(articles = emptyList()),
            onEvent = {},
        )
    }
}
