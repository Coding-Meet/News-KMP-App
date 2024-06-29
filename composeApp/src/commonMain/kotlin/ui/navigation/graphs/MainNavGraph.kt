package ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.database.NewsDatabase
import ui.navigation.Graph
import ui.navigation.MainRouteScreen
import ui.bookmark.BookmarkScreen
import ui.headline.HeadlineScreen
import ui.search.SearchScreen
import utils.FadeIn
import utils.FadeOut

/**
 * Created 28-02-2024 at 03:05 pm
 */

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    innerPadding: PaddingValues,
    newsDatabase: NewsDatabase,
) {
    NavHost(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Headline.route,
        enterTransition = { FadeIn },
        exitTransition = { FadeOut },
    ) {

        composable(route = MainRouteScreen.Headline.route) {
            HeadlineScreen(rootNavController)
        }

        composable(route = MainRouteScreen.Search.route) {
            SearchScreen(rootNavController)
        }

        composable(route = MainRouteScreen.Bookmark.route) {
            BookmarkScreen(rootNavController,newsDatabase)
        }
    }

}