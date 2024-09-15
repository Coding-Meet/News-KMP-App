package ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import data.model.Article
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import theme.cardMinSize
import theme.mediumPadding
import ui.navigation.NewsRouteScreen
import utils.randomUUIDStr


@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    rootNavController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articleList, key = {
            it.publishedAt + randomUUIDStr()
        }) { item ->
            ArticleItem(article = item, onClick = {

                val articleStr = Json.encodeToString(item)
                rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                rootNavController.navigate(NewsRouteScreen.NewsDetail.route)
            })
        }
    }

}