package ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import di.koinViewModel
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_browse
import news_kmp_app.composeapp.generated.resources.ic_network_error
import news_kmp_app.composeapp.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource
import theme.xSmallPadding
import ui.common.ArticleListScreen
import ui.common.EmptyContent
import ui.common.ShimmerEffect
import ui.search.components.SearchBar

@Composable
fun SearchScreen(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    val searchViewModel = koinViewModel<SearchViewModel>()
    val uiState by searchViewModel.newsStateFlow.collectAsState()


    Column(
        modifier =  Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        SearchBar(
            text = searchViewModel.searchQuery,
            onValueChange = { query ->
                searchViewModel.searchQuery = query
            },
            onSearch = { query ->
                if (query.trim().isNotEmpty()) {
                    searchViewModel.searchQueryNews(query.trim())
                }
            },
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.type_to_search),
                        icon = Res.drawable.ic_browse, onRetryClick = {
                            if (searchViewModel.searchQuery.trim().isNotEmpty()) {
                                searchViewModel.searchQueryNews(searchViewModel.searchQuery.trim())
                            }
                        })
                } else {
                    ArticleListScreen(articleList, navController)
                }
            },
            onError = {
                EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                    if (searchViewModel.searchQuery.trim().isNotEmpty()) {
                        searchViewModel.searchQueryNews(searchViewModel.searchQuery.trim())
                    }
                })
            }
        )
    }
}