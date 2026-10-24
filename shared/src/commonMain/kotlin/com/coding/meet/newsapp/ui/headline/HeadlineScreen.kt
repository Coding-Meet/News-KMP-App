package com.coding.meet.newsapp.ui.headline

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Feed
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.common.ArticleListScreen
import com.coding.meet.newsapp.ui.common.EmptyContent
import com.coding.meet.newsapp.ui.common.ShimmerEffect
import com.coding.meet.newsapp.utils.categoryList
import com.coding.meet.newsapp.utils.navigationItemsLists
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

import com.coding.meet.newsapp.designsystem.component.chip.NewsCategoryChip
import com.coding.meet.newsapp.designsystem.theme.spacing

@Composable
fun HeadlineScreen(
    state: HeadlineState,
    onEvent: (HeadlineEvent) -> Unit,
    onSettingsClick: (() -> Unit)? = null,
) {
    val spacing = MaterialTheme.spacing
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = stringResource(navigationItemsLists[0].title),
                actions = {
                    if (onSettingsClick != null) {
                        IconButton(onClick = onSettingsClick) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = spacing.sm),
                horizontalArrangement = Arrangement.spacedBy(spacing.xs, Alignment.CenterHorizontally),
            ) {
                items(categoryList, key = { it }) { category ->
                    NewsCategoryChip(
                        label = category,
                        selected = state.selectedCategory == category,
                        onClick = { onEvent(HeadlineEvent.SelectCategory(category)) },
                    )
                }
            }

            when {
                state.isLoading -> ShimmerEffect()
                state.error != null -> EmptyContent(
                    message = state.error,
                    icon = Icons.Outlined.CloudOff,
                    onRetryClick = { onEvent(HeadlineEvent.Retry) },
                )
                state.articles.isEmpty() -> EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Icons.AutoMirrored.Outlined.Feed,
                    onRetryClick = { onEvent(HeadlineEvent.Retry) },
                )
                else -> ArticleListScreen(
                    articleList = state.articles,
                    onArticleClick = { article -> onEvent(HeadlineEvent.OpenArticle(article)) },
                )
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun HeadlineScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        HeadlineScreen(
            state = HeadlineState(
                articles = previewArticles,
                selectedCategory = "Technology",
            ),
            onEvent = {},
            onSettingsClick = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun HeadlineScreenLoadingPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        HeadlineScreen(
            state = HeadlineState(isLoading = true, selectedCategory = "Technology"),
            onEvent = {},
        )
    }
}
