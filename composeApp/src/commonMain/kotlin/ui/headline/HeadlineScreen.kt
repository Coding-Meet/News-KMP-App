package ui.headline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import di.koinViewModel
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.ic_browse
import news_kmp_app.composeapp.generated.resources.ic_network_error
import news_kmp_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource
import theme.xSmallPadding
import ui.common.ArticleListScreen
import ui.common.EmptyContent
import ui.common.ShimmerEffect
import utils.categoryList

@Composable
fun HeadlineScreen(navController: NavController,paddingValues: PaddingValues) {

    val headlineViewModel = koinViewModel<HeadlineViewModel>()

    val uiState by headlineViewModel.newsStateFlow.collectAsState()


    Column(
        Modifier.padding(paddingValues)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding, Alignment.CenterHorizontally)
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    selected = headlineViewModel.category == category,
                    onClick = {
                        headlineViewModel.category = category
                        headlineViewModel.getHeadlines(headlineViewModel.category)
                    }, label = {
                        Text(category)
                    })
            }
        }
        uiState.DisplayResult(
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse,
                        onRetryClick = {
                            headlineViewModel.getHeadlines(headlineViewModel.category)
                        }
                    )
                } else {
                    ArticleListScreen(articleList, navController)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        headlineViewModel.getHeadlines(headlineViewModel.category)
                    }
                )
            }
        )
    }
}