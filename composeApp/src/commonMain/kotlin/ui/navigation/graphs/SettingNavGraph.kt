package ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ui.navigation.Graph
import ui.navigation.NewsRouteScreen
import ui.navigation.SettingRouteScreen
import ui.setting.SettingScreen
import ui.setting.SettingViewModel
import utils.FadeIn
import utils.FadeOut

/**
 * Created 28-02-2024 at 03:05 pm
 */

fun NavGraphBuilder.settingScreenNavGraph(rootNavController: NavHostController, settingViewModel: SettingViewModel) {
    navigation(
        route = Graph.SettingScreenGraph,
        startDestination = NewsRouteScreen.NewsDetail.route,
    ) {
        composable(
            route = SettingRouteScreen.SettingDetail.route,
            enterTransition = { FadeIn },
            exitTransition = { FadeOut },
        ) {
            SettingScreen(navController = rootNavController,settingViewModel)
        }
    }
}