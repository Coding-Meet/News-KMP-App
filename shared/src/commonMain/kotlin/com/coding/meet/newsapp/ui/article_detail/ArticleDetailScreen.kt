package com.coding.meet.newsapp.ui.article_detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.coding.meet.newsapp.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.ui.common.PulseAnimation
import com.coding.meet.newsapp.utils.previewArticles
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.go_back
import news_kmp_app.shared.generated.resources.ic_bookmark_filled
import news_kmp_app.shared.generated.resources.ic_bookmark_outlined
import news_kmp_app.shared.generated.resources.ic_browse
import news_kmp_app.shared.generated.resources.logo
import news_kmp_app.shared.generated.resources.news_detail
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArticleDetailScreen(
    state: ArticleDetailState,
    onEvent: (ArticleDetailEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = stringResource(Res.string.news_detail),
                navigationIcon = {
                    IconButton(onClick = { onEvent(ArticleDetailEvent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEvent(ArticleDetailEvent.ShareArticle) }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    IconButton(onClick = { onEvent(ArticleDetailEvent.OpenInBrowser) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    IconButton(onClick = { onEvent(ArticleDetailEvent.ToggleBookmark) }) {
                        Icon(
                            painter = painterResource(
                                if (state.isBookmarked) Res.drawable.ic_bookmark_filled
                                else Res.drawable.ic_bookmark_outlined
                            ),
                            contentDescription = null,
                            tint = if (state.isBookmarked) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onBackground,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        state.article?.let { article ->
            ArticleDetailContent(
                article = article,
                innerPadding = innerPadding,
            )
        }
    }
}

@Composable
private fun ArticleDetailContent(
    article: Article,
    innerPadding: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding() + 16.dp,
            start = 20.dp,
            end = 20.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            ArticleImage(
                imageUrl = article.urlToImage,
                title = article.title,
            )
        }
        item {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        article.description?.let {
            item {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                )
            }
        }
        article.content?.let {
            item {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        item {
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Composable
private fun ArticleImage(imageUrl: String?, title: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .wrapContentWidth()
            .widthIn(max = 720.dp)
            .aspectRatio(16f / 9f),
        contentAlignment = Alignment.Center
    ) {
        var imageLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
            onSuccess = {
                imageLoadResult =
                    if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(Exception("Invalid image size"))
                    }
            },
            onError = {
                imageLoadResult = Result.failure(it.result.throwable)
            },
        )
        val painterState by painter.state.collectAsState()
        val transition by animateFloatAsState(
            targetValue = if (painterState is AsyncImagePainter.State.Success) 1f else 0f,
            animationSpec = tween(durationMillis = 800),
        )
        when (val result = imageLoadResult) {
            null -> PulseAnimation(modifier = Modifier.fillMaxSize())
            else -> Image(
                painter = if (result.isSuccess) painter else painterResource(Res.drawable.logo),
                contentDescription = title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.wrapContentSize().graphicsLayer {
                    if (result.isSuccess) {
                        rotationX = (1f - transition) * 30f
                        val scale = 0.8f + (0.2f * transition)
                        scaleX = scale
                        scaleY = scale
                    }
                },
            )
        }

    }
}

@AllAdaptivePreviews
@Composable
private fun ArticleDetailScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        ArticleDetailScreen(
            state = ArticleDetailState(
                article = previewArticles[0],
                isBookmarked = true,
                isLoading = false,
            ),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun ArticleDetailScreenLoadingPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        ArticleDetailScreen(
            state = ArticleDetailState(isLoading = true),
            onEvent = {},
        )
    }
}
