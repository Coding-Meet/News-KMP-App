package com.coding.meet.newsapp.ui.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
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
import com.coding.meet.newsapp.ui.search.components.SearchBar
import com.coding.meet.newsapp.utils.navigationItemsLists
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.ic_browse
import news_kmp_app.shared.generated.resources.ic_network_error
import news_kmp_app.shared.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(title = stringResource(navigationItemsLists[1].title))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            SearchBar(
                text = state.query,
                onValueChange = { onEvent(SearchEvent.QueryChanged(it)) },
                onSearch = { onEvent(SearchEvent.Search) },
            )
            when {
                state.isIdle -> EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false,
                )
                state.isLoading -> ShimmerEffect()
                state.error != null -> EmptyContent(
                    message = state.error,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = { onEvent(SearchEvent.Retry) },
                )
                state.articles.isEmpty() -> EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false,
                )
                else -> ArticleListScreen(
                    articleList = state.articles,
                    onArticleClick = { article -> onEvent(SearchEvent.OpenArticle(article)) },
                )
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun SearchScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SearchScreen(
            state = SearchState(articles = previewArticles, query = "space", isIdle = false),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SearchScreenIdlePreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SearchScreen(
            state = SearchState(isIdle = true),
            onEvent = {},
        )
    }
}
