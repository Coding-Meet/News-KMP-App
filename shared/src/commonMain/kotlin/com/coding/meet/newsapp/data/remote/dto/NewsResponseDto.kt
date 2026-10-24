package com.coding.meet.newsapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int,
)
