package com.coding.meet.newsapp.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.coding.meet.newsapp.ui.article_detail.ArticleDetailScreen
import com.coding.meet.newsapp.ui.bookmark.BookmarkScreen
import com.coding.meet.newsapp.ui.headline.HeadlineScreen
import com.coding.meet.newsapp.ui.navigation.Route
import com.coding.meet.newsapp.ui.search.SearchScreen
import com.coding.meet.newsapp.ui.setting.SettingScreen

@Composable
fun NavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Headline,
    ) {
        composable<Route.Headline> {
            HeadlineScreen(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
                onSettingsClick = { rootNavController.navigate(Route.SettingDetail) },
            )
        }
        composable<Route.Search> {
            SearchScreen(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
            )
        }
        composable<Route.Bookmark> {
            BookmarkScreen(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
            )
        }
        composable<Route.NewsDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.NewsDetail>()
            ArticleDetailScreen(
                articleId = route.articleId,
                onNavigateBack = { rootNavController.popBackStack() },
            )
        }
        composable<Route.SettingDetail> {
            SettingScreen(
                onNavigateBack = { rootNavController.popBackStack() },
            )
        }
    }
}
