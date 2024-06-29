package ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import data.model.Article
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import theme.xLargePadding
import ui.navigation.NewsRouteScreen
import utils.Type
import utils.getType
import utils.randomUUIDStr


@Composable
fun ArticleListScreen(articleList: List<Article>,navController: NavController) {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding),
    ){
        items(articleList, key = {
            it.publishedAt+ randomUUIDStr()
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