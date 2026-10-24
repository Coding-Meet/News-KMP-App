package com.coding.meet.newsapp.ui.setting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.ic_light_mode
import org.jetbrains.compose.resources.painterResource

import com.coding.meet.newsapp.designsystem.theme.spacing

@Composable
fun SettingItem(
    onClick: () -> Unit,
    painter: Painter,
    itemName: String,
    itemColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val spacing = MaterialTheme.spacing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .padding(horizontal = spacing.lg, vertical = spacing.md),
        horizontalArrangement = Arrangement.spacedBy(spacing.md, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = itemColor),
        )
        Text(
            text = itemName,
            color = itemColor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingItemPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingItem(
            onClick = {},
            painter = painterResource(Res.drawable.ic_light_mode),
            itemName = "Theme",
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingItemDestructivePreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingItem(
            onClick = {},
            painter = painterResource(Res.drawable.ic_light_mode),
            itemName = "Delete Bookmarks",
            itemColor = MaterialTheme.colorScheme.error,
        )
    }
}
