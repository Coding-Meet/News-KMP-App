package com.coding.meet.newsapp.ui.search

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
import com.coding.meet.newsapp.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.common.ArticleListScreen
import com.coding.meet.newsapp.ui.common.EmptyContent
import com.coding.meet.newsapp.ui.common.ShimmerEffect
import com.coding.meet.newsapp.ui.search.components.SearchBar
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.utils.navigationItemsLists
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
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
                    icon = Icons.Outlined.Search,
                    isOnRetryBtnVisible = false,
                )

                state.isLoading -> ShimmerEffect()
                state.error != null -> EmptyContent(
                    message = state.error,
                    icon = Icons.Outlined.CloudOff,
                    onRetryClick = { onEvent(SearchEvent.Retry) },
                )

                state.articles.isEmpty() -> EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Icons.Outlined.SearchOff,
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
