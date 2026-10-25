package com.coding.meet.newsapp.ui.screen.setting.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.designsystem.theme.spacing

@Composable
fun SettingItem(
    onClick: () -> Unit,
    icon: ImageVector,
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
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = itemColor,
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
            icon = Icons.Outlined.Palette,
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
            icon = Icons.Outlined.Delete,
            itemName = "Delete Bookmarks",
            itemColor = MaterialTheme.colorScheme.error,
        )
    }
}
