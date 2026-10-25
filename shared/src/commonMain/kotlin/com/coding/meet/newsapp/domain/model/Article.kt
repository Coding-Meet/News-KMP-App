package com.coding.meet.newsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String,
)

@Serializable
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
)
