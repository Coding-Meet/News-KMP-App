package com.coding.meet.newsapp.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.coding.meet.newsapp.ui.article_detail.ArticleDetailRoute
import com.coding.meet.newsapp.ui.bookmark.BookmarkRoute
import com.coding.meet.newsapp.ui.headline.HeadlineRoute
import com.coding.meet.newsapp.ui.navigation.Route
import com.coding.meet.newsapp.ui.search.SearchRoute
import com.coding.meet.newsapp.ui.setting.SettingRoute

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
            HeadlineRoute(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
                onSettingsClick = { rootNavController.navigate(Route.SettingDetail) },
            )
        }
        composable<Route.Search> {
            SearchRoute(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
            )
        }
        composable<Route.Bookmark> {
            BookmarkRoute(
                onArticleClick = { articleId ->
                    rootNavController.navigate(Route.NewsDetail(articleId))
                },
            )
        }
        composable<Route.NewsDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.NewsDetail>()
            ArticleDetailRoute(
                articleId = route.articleId,
                onNavigateBack = { rootNavController.popBackStack() },
            )
        }
        composable<Route.SettingDetail> {
            SettingRoute(
                onNavigateBack = { rootNavController.popBackStack() },
            )
        }
    }
}
