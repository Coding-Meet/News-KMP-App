package ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import data.database.NewsDatabase
import data.model.Article
import kotlinx.serialization.json.Json
import ui.article.ArticleScreen
import ui.navigation.Graph
import ui.navigation.NewsRouteScreen

/**
 * Created 28-02-2024 at 03:05 pm
 */

fun NavGraphBuilder.newsScreenNavGraph(rootNavController: NavHostController, newsDatabase: NewsDatabase) {
    navigation(
        route = Graph.NewsScreenGraph,
        startDestination = NewsRouteScreen.NewsDetail.route,

        ) {
        composable(
            route = NewsRouteScreen.NewsDetail.route,
        ) {

            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                ArticleScreen(navController = rootNavController, newsDatabase, currentArticle)
            }
        }
    }
}