package ui.article_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import data.model.Article
import di.koinViewModel
import news_kmp_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.mediumPadding
import theme.xLargePadding
import utils.Type
import utils.getScreenSize
import utils.getType
import utils.shareLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    currentArticle: Article
) {
    val articleDetailViewModel = koinViewModel<ArticleDetailViewModel>()
    val isDesktop by remember {
        derivedStateOf { getType() == Type.Desktop }
    }

    val screenSize = getScreenSize()

    LaunchedEffect(Unit) {
        articleDetailViewModel.isArticleBookmark(currentArticle)
    }
    val url = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {
                        shareLink(currentArticle.url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = {
                        url.openUri(currentArticle.url)
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = {
                        articleDetailViewModel.bookmarkArticle(currentArticle)
                    }) {
                        Icon(
                            painter = painterResource(
                                if (articleDetailViewModel.isBookmarked) Res.drawable.ic_bookmark_filled
                                else Res.drawable.ic_bookmark_outlined
                            ),
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        val imageSize by remember(screenSize, isDesktop, innerPadding) {
            derivedStateOf {
                (if (isDesktop) screenSize.height else screenSize.width) - (innerPadding.calculateTopPadding() + mediumPadding + 100.dp)
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = xLargePadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageSize)
                        .clip(MaterialTheme.shapes.large)
                        .background(color = Color.Gray),
                    model = currentArticle.urlToImage,
                    error = painterResource(Res.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            item {
                Text(
                    text = currentArticle.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            currentArticle.description?.let { content ->
                item {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            currentArticle.publishedAt.let { content ->
                item {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

