package ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import theme.*


@Composable
fun ShimmerEffect() {
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
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
        userScrollEnabled = false
    ) {
        repeat(30) {
            item {
                ArticleCardShimmerEffect(brush)
            }
        }
    }
}

@Composable
fun ArticleCardShimmerEffect(brush: Brush) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .background(brush, RoundedCornerShape(10))
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxxLargePadding)
                    .background(brush, RoundedCornerShape(10))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxLargePadding)
                    .background(brush, RoundedCornerShape(10))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(mediumPadding)
                    .background(brush, RoundedCornerShape(10))
            )
        }
    }
}