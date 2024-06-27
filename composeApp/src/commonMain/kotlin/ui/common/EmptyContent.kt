package ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyContent(
    message: String,
    icon: DrawableResource,
    onRetryClick: (() -> Unit)?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(icon),
            tint = if (!isSystemInDarkTheme()) LightGray else DarkGray,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = if (!isSystemInDarkTheme()) LightGray else DarkGray,
        )
        onRetryClick?.let {
            Button(onClick = it) {
                Text(
                    text = "Retry",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}