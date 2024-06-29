package ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import data.database.NewsDatabase
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_network_error
import news_kmp_app.composeapp.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import ui.common.ArticleListScreen
import ui.common.EmptyContent
import ui.common.ShimmerEffect

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
            EmptyContent(message = stringResource(Res.string.no_bookmarks), icon = Res.drawable.ic_network_error, onRetryClick = null)
        } else {
            ArticleListScreen(articleList,navController)
        }
    }, onError = {
        EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
            bookmarkViewModel.getArticles()
        })
    })

}