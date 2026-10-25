package com.coding.meet.newsapp.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.coding.meet.newsapp.domain.model.Article
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
                onArticleClick = { article ->
                    rootNavController.navigate(Route.NewsDetail.from(article))
                },
                onSettingsClick = { rootNavController.navigate(Route.SettingDetail) },
            )
        }
        composable<Route.Search> {
            SearchScreen(
                onArticleClick = { article ->
                    rootNavController.navigate(Route.NewsDetail.from(article))
                },
            )
        }
        composable<Route.Bookmark> {
            BookmarkScreen(
                onArticleClick = { article ->
                    rootNavController.navigate(Route.NewsDetail.from(article))
                },
            )
        }
        composable<Route.NewsDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.NewsDetail>()
            ArticleDetailScreen(
                article = route.toArticle(),
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
