package ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.database.NewsDatabase
import ui.MainScreen
import ui.navigation.Graph

/**
 * Created 28-02-2024 at 03:04 pm
 */

@Composable
fun RootNavGraph(newsDatabase: NewsDatabase) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        composable(route = Graph.MainScreenGraph){
            MainScreen(rootNavController,newsDatabase=newsDatabase)
        }
        newsScreenNavGraph(rootNavController,newsDatabase)
    }
}