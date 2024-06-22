
package ui.article.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import news_kmp_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleTopBar(
    isBookmarked: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowsingClick: () -> Unit,
    onBookMarkClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_browse),
                    contentDescription = null,
                )
            }
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = painterResource(
                        if (isBookmarked) Res.drawable.ic_bookmark_filled
                        else Res.drawable.ic_bookmark_outlined
                    ),
                    contentDescription = null,
                )
            }
        },
    )
}


@Composable
fun DetailsTopBarPreview() {
    NewsAppTheme {
        ArticleTopBar(
            isBookmarked = false,
            onBackClick = {},
            onShareClick = {},
            onBookMarkClick = {},
            onBrowsingClick = {}
        )
    }
}