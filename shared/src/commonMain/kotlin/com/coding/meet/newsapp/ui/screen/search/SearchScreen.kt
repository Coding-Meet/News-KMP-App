package com.coding.meet.newsapp.ui.screen.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
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
import com.coding.meet.newsapp.ui.screen.search.components.SearchBar
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.no_news
import news_kmp_app.shared.generated.resources.retry
import news_kmp_app.shared.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreen(
    onArticleClick: (article: Article) -> Unit,
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SearchEffect.NavigateToDetail -> onArticleClick(effect.article)
            }
        }
    }

    SearchScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}


@Composable
fun SearchScreenContent(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(title = stringResource(NavigationItem.Search.title))
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
                state.isIdle -> NewsEmptyView(
                    title = stringResource(Res.string.type_to_search),
                    icon = Icons.Outlined.Search,
                )

                state.isLoading -> ShimmerEffect()
                state.error != null -> NewsErrorView(
                    message = state.error,
                    icon = Icons.Outlined.CloudOff,
                    retryLabel = stringResource(Res.string.retry),
                    onRetry = { onEvent(SearchEvent.Retry) },
                )

                state.articles.isEmpty() -> NewsEmptyView(
                    title = stringResource(Res.string.no_news),
                    icon = Icons.Outlined.SearchOff,
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
private fun SearchScreenContentPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SearchScreenContent(
            state = SearchState(articles = previewArticles, query = "space", isIdle = false),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SearchScreenContentIdlePreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SearchScreenContent(
            state = SearchState(isIdle = true),
            onEvent = {},
        )
    }
}
