package ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import theme.*
import utils.Type
import utils.getType

fun Modifier.shimmerEffect() = composed {

    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Reverse
        ),
    )
    val shimmerColors = listOf(
        shimmer.copy(alpha = 0.3f),
        shimmer.copy(alpha = 0.5f),
        shimmer.copy(alpha = 1.0f),
        shimmer.copy(alpha = 0.5f),
        shimmer.copy(alpha = 0.3f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation + 100f),
        tileMode = TileMode.Mirror,
    )
    background(brush, RoundedCornerShape(10))
}

@Composable
fun ShimmerEffect() {
    val isDesktop by remember {
        mutableStateOf(getType() == Type.Desktop)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
        userScrollEnabled = false
    ) {
        repeat( 12 ) {
            item {
                ArticleCardShimmerEffect()
            }
        }
    }
}

@Composable
fun ArticleCardShimmerEffect() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .shimmerEffect()
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxxLargePadding)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxLargePadding)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(mediumPadding)
                    .shimmerEffect()
            )
        }
    }
}