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
import news_kmp_app.composeapp.generated.resources.*
import theme.xLargePadding
import ui.common.ArticleItem
import ui.common.EmptyContent
import ui.navigation.NewsRouteScreen

@Composable
fun BookmarkScreen(
    navController: NavController,
    newsDatabase: NewsDatabase

) {
    val bookmarkViewModel = viewModel {
        BookmarkViewModel(newsDatabase)
    }
    val articles = bookmarkViewModel.state.value.articles

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(xLargePadding),
        verticalArrangement = Arrangement.spacedBy(xLargePadding)
    ) {
        if (articles.isNotEmpty()) {
            items(articles) { item ->
                ArticleItem(
                    article = item,
                    onClick = {
                        navController.navigate(NewsRouteScreen.NewsDetail.route)
                    }
                )
            }
        }
    }

    if (articles.isEmpty()) {
        EmptyContent(
            message = "No bookmarks yet!",
            icon = Res.drawable.ic_bookmark_outlined,
            onRetryClick = null
        )
    }
}