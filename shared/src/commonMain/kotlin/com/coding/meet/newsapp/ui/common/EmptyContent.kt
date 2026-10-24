package com.coding.meet.newsapp.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.retry
import org.jetbrains.compose.resources.stringResource

import com.coding.meet.newsapp.designsystem.component.button.NewsPrimaryButton
import com.coding.meet.newsapp.designsystem.theme.spacing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun EmptyContent(
    message: String,
    icon: ImageVector,
    isOnRetryBtnVisible: Boolean = true,
    onRetryClick: (() -> Unit) = {},
) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(spacing.md))
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (isOnRetryBtnVisible) {
            Spacer(modifier = Modifier.height(spacing.lg))
            NewsPrimaryButton(
                text = stringResource(Res.string.retry),
                onClick = onRetryClick,
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun EmptyContentWithRetryPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        EmptyContent(
            message = "Something went wrong. Please try again.",
            icon = Icons.Outlined.CloudOff,
            isOnRetryBtnVisible = true,
            onRetryClick = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun EmptyContentNoRetryPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        EmptyContent(
            message = "No bookmarks yet.",
            icon = Icons.Outlined.BookmarkBorder,
            isOnRetryBtnVisible = false,
        )
    }
}
