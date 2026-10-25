package com.coding.meet.newsapp.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.designsystem.theme.shimmerColors
import com.coding.meet.newsapp.ui.designsystem.theme.spacing

@Composable
fun ShimmerEffect() {
    val spacing = MaterialTheme.spacing
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Reverse
        ),
    )
    val brush by remember {
        derivedStateOf {
            Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnimation, translateAnimation),
                end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                tileMode = TileMode.Mirror,
            )
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(360.dp),
        verticalItemSpacing = spacing.sm,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
        contentPadding = PaddingValues(spacing.md),
        userScrollEnabled = false,
    ) {
        repeat(30) {
            item { ArticleCardShimmerEffect(brush) }
        }
    }
}

@Composable
fun ArticleCardShimmerEffect(brush: Brush) {
    val spacing = MaterialTheme.spacing
    val shape = MaterialTheme.shapes.medium
    Row(
        modifier = Modifier.padding(spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .background(brush, shape)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xs),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(brush, shape)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(brush, shape)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .background(brush, shape)
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun ShimmerEffectPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        ShimmerEffect()
    }
}
