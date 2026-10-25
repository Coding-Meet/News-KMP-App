package com.coding.meet.newsapp.ui.screen.headline

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.ui.components.ArticleListScreen
import com.coding.meet.newsapp.ui.components.ShimmerEffect
import com.coding.meet.newsapp.ui.designsystem.component.chip.NewsCategoryChip
import com.coding.meet.newsapp.ui.designsystem.component.state.NewsEmptyView
import com.coding.meet.newsapp.ui.designsystem.component.state.NewsErrorView
import com.coding.meet.newsapp.ui.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.designsystem.theme.spacing
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.utils.categoryList
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.no_news
import news_kmp_app.shared.generated.resources.retry
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HeadlineScreen(
    onArticleClick: (article: Article) -> Unit,
    onSettingsClick: () -> Unit,
) {
    val viewModel = koinViewModel<HeadlineViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HeadlineEffect.NavigateToDetail -> onArticleClick(effect.article)
            }
        }
    }

    HeadlineScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
        onSettingsClick = onSettingsClick,
    )
}


@Composable
fun HeadlineScreenContent(
    state: HeadlineState,
    onEvent: (HeadlineEvent) -> Unit,
    onSettingsClick: (() -> Unit)? = null,
) {
    val spacing = MaterialTheme.spacing
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = stringResource(NavigationItem.Headlines.title),
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
                horizontalArrangement = Arrangement.spacedBy(
                    spacing.xs,
                    Alignment.CenterHorizontally
                ),
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
                state.error != null -> NewsErrorView(
                    message = state.error,
                    icon = Icons.Outlined.CloudOff,
                    retryLabel = stringResource(Res.string.retry),
                    onRetry = { onEvent(HeadlineEvent.Retry) },
                )

                state.articles.isEmpty() -> NewsEmptyView(
                    title = stringResource(Res.string.no_news),
                    icon = Icons.AutoMirrored.Outlined.Feed,
                    actionLabel = stringResource(Res.string.retry),
                    onAction = { onEvent(HeadlineEvent.Retry) },
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
private fun HeadlineScreenContentPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        HeadlineScreenContent(
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
private fun HeadlineScreenContentLoadingPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        HeadlineScreenContent(
            state = HeadlineState(isLoading = true, selectedCategory = "Technology"),
            onEvent = {},
        )
    }
}
