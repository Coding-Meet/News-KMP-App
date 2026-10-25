package com.coding.meet.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coding.meet.newsapp.ui.screen.article_detail.ArticleDetailScreen
import com.coding.meet.newsapp.ui.screen.bookmark.BookmarkScreen
import com.coding.meet.newsapp.ui.screen.headline.HeadlineScreen
import com.coding.meet.newsapp.ui.screen.search.SearchScreen
import com.coding.meet.newsapp.ui.screen.setting.SettingScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Headline,
    ) {
        composable<Route.Headline> {
            HeadlineScreen(
                onArticleClick = { article ->
                    navController.navigate(Route.NewsDetail.from(article))
                },
                onSettingsClick = { navController.navigate(Route.SettingDetail) },
            )
        }
        composable<Route.Search> {
            SearchScreen(
                onArticleClick = { article ->
                    navController.navigate(Route.NewsDetail.from(article))
                },
            )
        }
        composable<Route.Bookmark> {
            BookmarkScreen(
                onArticleClick = { article ->
                    navController.navigate(Route.NewsDetail.from(article))
                },
            )
        }
        composable<Route.NewsDetail> {
            ArticleDetailScreen(
                onNavigateBack = { navController.navigateUp() },
            )
        }
        composable<Route.SettingDetail> {
            SettingScreen(
                onNavigateBack = { navController.navigateUp() },
            )
        }
    }
}
