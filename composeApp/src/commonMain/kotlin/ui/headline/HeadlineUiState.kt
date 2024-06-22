package ui.headline

import data.model.Article
import kotlin.random.Random

data class HeadlineUiState(
    val isLoading: Boolean = true,
    val message: String = "",
    val articles: List<Article> = listOf(
        Article(
            source = "My news",
            author = "The author",
            title = "This is the main news title headline. This is the main news title headline.",
            description = "This is the main news description. This is the main news description. This is the main news description",
            url = "",
            urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
            publishedAt = Random(100).nextInt().toString(),
            content = "What is the content?"
        ),
        Article(
            source = "My news",
            author = "The author",
            title = "This is the main news title headline. This is the main news title headline.",
            description = "This is the main news description. This is the main news description. This is the main news description",
            url = "",
            urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
            publishedAt = Random(100).nextInt().toString(),
            content = "What is the content?"
        ),
        Article(
            source = "My news",
            author = "The author",
            title = "This is the main news title headline. This is the main news title headline.",
            description = "This is the main news description. This is the main news description. This is the main news description",
            url = "",
            urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
            publishedAt = Random(100).nextInt().toString(),
            content = "What is the content?"
        ),
        Article(
            source = "My news",
            author = "The author",
            title = "This is the main news title headline. This is the main news title headline.",
            description = "This is the main news description. This is the main news description. This is the main news description",
            url = "",
            urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
            publishedAt = Random(100).nextInt().toString(),
            content = "What is the content?"
        ),
    )
)