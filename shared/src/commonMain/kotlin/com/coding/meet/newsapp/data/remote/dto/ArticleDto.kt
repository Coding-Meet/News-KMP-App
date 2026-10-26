package com.coding.meet.newsapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String,
)

@Serializable
data class ArticleDto(
    @SerialName("author") val author: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("source") val source: SourceDto,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val urlToImage: String? = null,
)
