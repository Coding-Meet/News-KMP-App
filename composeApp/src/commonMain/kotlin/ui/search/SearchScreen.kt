package ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import news_kmp_app.composeapp.generated.resources.*
import theme.mediumPadding
import theme.xLargePadding
import ui.common.ArticleItem
import ui.common.EmptyContent
import ui.common.ShimmerEffect
import ui.navigation.NewsRouteScreen
import ui.search.components.SearchBar

@Composable
fun SearchScreen(
    navController: NavController,
) {
    val searchViewModel = viewModel { SearchViewModel() }
    val uiState by searchViewModel.newsStateFlow.collectAsState()

    val searchQuery by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding),
            text = searchQuery,
            onSearch = { query ->
                if (query.trim().isNotEmpty()){
                    searchViewModel.searchQueryNews(query.trim())
                }
            },
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = "Type to search!",
                    icon = Res.drawable.ic_browse,
                    onRetryClick = null
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = "Type to search!",
                        icon = Res.drawable.ic_browse,
                        onRetryClick = null
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(xLargePadding),
                        verticalArrangement = Arrangement.spacedBy(xLargePadding)
                    ) {
                        items(articleList,key={
                            it.publishedAt
                        }) { item ->
                            ArticleItem(article = item, onClick = {
                                navController.navigate(NewsRouteScreen.NewsDetail.route)
                            })
                        }
                    }
                }
            },
            onError = {
                EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                    if (searchQuery.trim().isNotEmpty()){
                        searchViewModel.searchQueryNews(searchQuery.trim())
                    }
                })
            }
        )
    }
}