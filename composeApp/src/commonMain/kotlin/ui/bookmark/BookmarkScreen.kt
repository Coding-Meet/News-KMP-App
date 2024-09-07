package ui.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import di.koinViewModel
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_browse
import news_kmp_app.composeapp.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import ui.common.ArticleListScreen
import ui.common.EmptyContent
import ui.common.ShimmerEffect

@Composable
fun BookmarkScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    val bookmarkViewModel = koinViewModel<BookmarkViewModel>()

    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()

    Column(
        modifier =  Modifier.padding(paddingValues),
    ) {
        uiState.DisplayResult(onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_bookmarks),
                    icon = Res.drawable.ic_browse, isOnRetryBtnVisible = false
                )
            } else {
                ArticleListScreen(articleList, navController)
            }
        }, onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_browse, onRetryClick = {
                bookmarkViewModel.getArticles()
            })
        })
    }

}