package ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ui.MainScaffold
import ui.navigation.Graph
import ui.navigation.MainRouteScreen
import ui.bookmark.BookmarkScreen
import ui.headline.HeadlineScreen
import ui.search.SearchScreen

/**
 * Created 28-02-2024 at 03:05 pm
 */

fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController
) {
    navigation(
        startDestination = MainRouteScreen.Headline.route,
        route = Graph.MainScreenGraph
    ) {
        composable(route = MainRouteScreen.Headline.route) {
            MainScaffold(rootNavController) {paddingValues ->
                HeadlineScreen(navController = rootNavController, paddingValues =paddingValues)
            }
        }
        composable(route = MainRouteScreen.Search.route) {
            MainScaffold(rootNavController) {paddingValues ->
                SearchScreen(navController = rootNavController, paddingValues =paddingValues)
            }
        }
        composable(route = MainRouteScreen.Bookmark.route) {
            MainScaffold(rootNavController) {paddingValues ->
                BookmarkScreen(navController = rootNavController, paddingValues =paddingValues )
            }
        }
    }

}