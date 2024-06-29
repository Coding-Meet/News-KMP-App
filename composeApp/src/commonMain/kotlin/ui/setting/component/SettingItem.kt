package ui.setting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import theme.mediumPadding
import theme.xLargePadding

@Composable
fun SettingItem(
    onClick: () -> Unit,
    painter: Painter,
    itemName: String,
    itemColor: Color = MaterialTheme.colorScheme.onSurface
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(mediumPadding),
        horizontalArrangement = Arrangement.spacedBy(mediumPadding, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(xLargePadding),
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = itemColor)
        )
        Text(
            text = itemName,
            color = itemColor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
    }
}