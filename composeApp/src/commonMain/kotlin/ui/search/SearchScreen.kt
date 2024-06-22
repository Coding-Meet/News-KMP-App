package ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import news_kmp_app.composeapp.generated.resources.*
import theme.mediumPadding
import ui.common.EmptyContent
import ui.search.components.SearchBar

@Composable
fun SearchScreen(
    navController: NavController,
) {
    val uiState by remember { mutableStateOf(SearchUiState()) }

    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding),
            text = uiState.searchQuery,
            onSearch = {
//                query -> onEvent(SearchUserEvent.SearchNews(query))
                       },
        )

        uiState.articles?.let { articles ->
//            ArticlesList(
//                articles = articles.collectAsLazyPagingItems(),
//                onClick = navigateToArticle
//            )
        }

        if (uiState.articles == null) {
            EmptyContent(
                message = "Type to search!",
                icon = Res.drawable.ic_browse,
                onRetryClick = null
            )
        }
    }
}