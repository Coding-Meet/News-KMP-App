package com.coding.meet.newsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class SourceEntity(
    val id: String?,
    val name: String,
)

@Entity(tableName = "articleTable")
data class ArticleEntity(
    @PrimaryKey val publishedAt: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val source: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String?,
)
