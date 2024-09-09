package ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import data.model.Article
import kotlinx.serialization.json.Json
import ui.article_detail.ArticleDetailScreen
import ui.common.MainScaffold
import ui.navigation.Graph
import ui.navigation.NewsRouteScreen
import ui.navigation.SettingRouteScreen
import ui.setting.SettingScreen
import ui.setting.SettingViewModel
import utils.bottomNavigationItemsList

/**
 * Created 28-02-2024 at 03:04 pm
 */
@Composable
fun RootNavGraph(settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
    val bottomNavRoute by remember {
        derivedStateOf {
            bottomNavigationItemsList.find { it.route == currentRoute }
        }
    }
    val bottomBarVisibility by remember {
        derivedStateOf {
            bottomNavRoute != null
        }
    }
    MainScaffold(
        rootNavController = rootNavController,
        currentRoute = currentRoute,
        bottomBarVisibility = bottomBarVisibility
    ) { innerPadding ->
        NavHost(
            navController = rootNavController,
            startDestination = Graph.MainScreenGraph,
        ) {
            mainNavGraph(rootNavController, innerPadding)
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
}