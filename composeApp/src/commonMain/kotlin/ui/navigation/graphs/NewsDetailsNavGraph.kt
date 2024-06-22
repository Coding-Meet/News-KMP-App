package ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import data.database.NewsDatabase
import ui.navigation.Graph
import ui.navigation.NewsRouteScreen
import ui.article.ArticleScreen

/**
 * Created 28-02-2024 at 03:05 pm
 */

fun NavGraphBuilder.newsScreenNavGraph(rootNavController: NavHostController, newsDatabase: NewsDatabase){
    navigation(
        route = Graph.NewsScreenGraph,
        startDestination = NewsRouteScreen.NewsDetail.route
    ){
        composable(route =  NewsRouteScreen.NewsDetail.route){
            ArticleScreen(navController = rootNavController,newsDatabase)
        }
    }
}