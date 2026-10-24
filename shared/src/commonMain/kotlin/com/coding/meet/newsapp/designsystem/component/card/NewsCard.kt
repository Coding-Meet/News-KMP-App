package com.coding.meet.newsapp.designsystem.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.coding.meet.newsapp.designsystem.theme.elevation

/**
 * Standard Material Design 3 Elevated Card for news articles and list cards.
 */
@Composable
fun NewsElevatedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.large,
    colors: CardColors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(
        defaultElevation = MaterialTheme.elevation.level1,
        pressedElevation = MaterialTheme.elevation.level2,
        focusedElevation = MaterialTheme.elevation.level2,
        hoveredElevation = MaterialTheme.elevation.level2,
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}

/**
 * Standard Material Design 3 Outlined Card for settings and secondary containers.
 */
@Composable
fun NewsOutlinedCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: CardColors = CardDefaults.outlinedCardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    border: BorderStroke = BorderStroke(
        width = MaterialTheme.elevation.level1,
        color = MaterialTheme.colorScheme.outlineVariant,
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = shape,
        colors = colors,
        border = border,
        content = content,
    )
}
