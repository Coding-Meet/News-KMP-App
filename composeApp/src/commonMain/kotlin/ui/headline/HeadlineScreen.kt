package ui.headline

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ui.navigation.NewsRouteScreen
import news_kmp_app.composeapp.generated.resources.*
import theme.xLargePadding
import ui.common.ArticleItem
import ui.common.EmptyContent
import ui.common.ShimmerEffect

@Composable
fun HeadlineScreen(navController: NavController) {
    var uiState by rememberSaveable { mutableStateOf(HeadlineUiState()) }

    LaunchedEffect(Unit) {
        delay(2000)
        uiState = uiState.copy(false)
    }
    Crossfade(
        modifier = Modifier.fillMaxSize(), targetState = uiState.isLoading, label = ""
    ) { isLoading ->
        if (isLoading) {
            ShimmerEffect()

        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(xLargePadding),
                verticalArrangement = Arrangement.spacedBy(xLargePadding)
            ) {
                items(uiState.articles) { item ->
                    ArticleItem(article = item, onClick = {
                        navController.navigate(NewsRouteScreen.NewsDetail.route)
                    })
                }
            }

            if (uiState.articles.isEmpty() && uiState.message.isNotEmpty()) {
                EmptyContent(message = uiState.message, icon = Res.drawable.ic_network_error, onRetryClick = { })
            }
        }
    }
}