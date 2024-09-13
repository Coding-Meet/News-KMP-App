package ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ui.navigation.Graph
import ui.navigation.MainRouteScreen
import ui.bookmark.BookmarkScreen
import ui.headline.HeadlineScreen
import ui.search.SearchScreen

/**
 * Created 28-02-2024 at 03:05 pm
 */

fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {
    navigation(
        startDestination = MainRouteScreen.Headline.route,
        route = Graph.MainScreenGraph
    ) {
        composable(route = MainRouteScreen.Headline.route) {
            HeadlineScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = MainRouteScreen.Search.route) {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = MainRouteScreen.Bookmark.route) {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
    }

}