package com.coding.meet.newsapp.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.utils.previewArticles

@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    onArticleClick: (Article) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(360.dp),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(articleList, key = { it.publishedAt }) { item ->
            ArticleItem(article = item, onClick = { onArticleClick(item) })
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun ArticleListScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        ArticleListScreen(
            articleList = previewArticles,
            onArticleClick = {},
        )
    }
}
