package ui.article

import data.model.Article
import kotlin.random.Random

data class ArticleUiState(
    val article: Article = Article(
        source = "My news",
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(1000).nextInt().toString(),
        content = "What is the content?"
    ),
    val isBookmarked: Boolean = false,
)
