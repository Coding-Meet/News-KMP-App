package ui.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import data.database.NewsDatabase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import news_kmp_app.composeapp.generated.resources.*
import theme.xLargePadding
import ui.common.ArticleItem
import ui.common.EmptyContent
import ui.common.ShimmerEffect
import ui.navigation.NewsRouteScreen

@Composable
fun BookmarkScreen(
    navController: NavController, newsDatabase: NewsDatabase

) {
    val bookmarkViewModel = viewModel {
        BookmarkViewModel(newsDatabase)
    }
    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()

    uiState.DisplayResult(onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent(message = "No bookmarks yet!", icon = Res.drawable.ic_network_error, onRetryClick = null)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(xLargePadding),
                verticalArrangement = Arrangement.spacedBy(xLargePadding)
            ) {
                items(articleList, key = {
                    it.publishedAt
                }) { item ->
                    ArticleItem(article = item, onClick = {
                        val articleStr = Json.encodeToString(item)
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set("article",articleStr)
                        }
                        navController.navigate(NewsRouteScreen.NewsDetail.route)
                    })
                }
            }
        }
    }, onError = {
        EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
            bookmarkViewModel.getArticles()
        })
    })

}