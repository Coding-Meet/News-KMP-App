package com.coding.meet.newsapp.ui.navigation

import com.coding.meet.newsapp.domain.model.Article
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64

sealed interface Route {

    @Serializable
    data object Headline : Route

    @Serializable
    data object Search : Route

    @Serializable
    data object Bookmark : Route

    @Serializable
    data class NewsDetail(val articleEncoded: String) : Route {
        companion object {
            fun from(article: Article): NewsDetail {
                val json = Json.encodeToString(article)
                val encoded = Base64.UrlSafe.encode(json.encodeToByteArray())
                return NewsDetail(encoded)
            }
        }

        fun toArticle(): Article {
            val json = Base64.UrlSafe.decode(articleEncoded).decodeToString()
            return Json.decodeFromString(json)
        }
    }

    @Serializable
    data object SettingDetail : Route

}