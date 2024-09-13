package ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
    ) {
        mainNavGraph(rootNavController = rootNavController, innerPadding = innerPadding)
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