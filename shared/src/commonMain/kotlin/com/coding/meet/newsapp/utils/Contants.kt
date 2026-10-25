package com.coding.meet.newsapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.unit.Dp
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.model.Source
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.dark_mode
import news_kmp_app.shared.generated.resources.light_mode
import news_kmp_app.shared.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource

const val BASE_URL = "https://newsapi.org/v2/"
const val DB_Name = "NewsDB"
const val dataStoreFileName = "setting.preferences_pb"

val categoryList = arrayListOf(
    "Business",
    "Entertainment",
    "General",
    "Health",
    "Science",
    "Sports",
    "Technology",
)

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode),
}

enum class Type {
    Mobile, Desktop
}

data class Size(
    val width: Dp,
    val height: Dp,
)

val previewArticles: List<Article> = listOf(
    Article(
        source = Source("src1", "My News"),
        author = "The Author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description.",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = "2026-10-15T17:53:55Z",
        content = "What is the content?",
    ),
    Article(
        source = Source("src2", "My News"),
        author = "The Author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description.",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = "2026-10-16T12:30:00Z",
        content = "What is the content?",
    ),
    Article(
        source = Source("src3", "My News"),
        author = "The Author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description.",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = "2026-10-17T08:15:00Z",
        content = "What is the content?",
    ),
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90))

val FadeOut = fadeOut(animationSpec = tween(90))
