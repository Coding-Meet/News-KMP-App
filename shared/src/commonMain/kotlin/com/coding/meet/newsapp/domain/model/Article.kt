package com.coding.meet.newsapp.domain.model

data class Source(
    val id: String?,
    val name: String,
)

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
