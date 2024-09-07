package ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.model.Article
import kotlinx.serialization.json.Json
import ui.article_detail.ArticleDetailScreen
import ui.navigation.Graph
import ui.navigation.NewsRouteScreen
import ui.navigation.SettingRouteScreen
import ui.setting.SettingScreen
import ui.setting.SettingViewModel

/**
 * Created 28-02-2024 at 03:04 pm
 */

@Composable
fun RootNavGraph(settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
    ) {
        mainNavGraph(rootNavController)
        composable(
            route = NewsRouteScreen.NewsDetail.route,
        ) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                ArticleDetailScreen(rootNavController, currentArticle)
            }
        }
        composable(
            route = SettingRouteScreen.SettingDetail.route,
        ) {
            SettingScreen(navController = rootNavController, settingViewModel)
        }
    }
}