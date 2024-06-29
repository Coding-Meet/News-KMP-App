package ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import theme.*
import utils.Type
import utils.getType

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = shimmer.copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}
@Composable
fun ShimmerEffect() {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding),
    ){
        repeat(12) {
            item {
                ArticleCardShimmerEffect()
            }
        }
    }
}
@Composable
fun ArticleCardShimmerEffect() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .clip(MaterialTheme.shapes.large)
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
                    .fillMaxWidth(0.3f)
                    .height(mediumPadding)
                    .shimmerEffect()
            )
        }
    }
}