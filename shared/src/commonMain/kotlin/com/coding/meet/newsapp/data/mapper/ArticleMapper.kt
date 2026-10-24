package com.coding.meet.newsapp.data.mapper

import com.coding.meet.newsapp.data.local.entity.ArticleEntity
import com.coding.meet.newsapp.data.local.entity.SourceEntity
import com.coding.meet.newsapp.data.remote.dto.ArticleDto
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.model.Source

// DTO → Domain
fun ArticleDto.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(id = source.id, name = source.name),
    title = title,
    url = url,
    urlToImage = urlToImage,
)

// Entity → Domain
fun ArticleEntity.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(id = source.id, name = source.name),
    title = title,
    url = url,
    urlToImage = urlToImage,
)

// Domain → Entity
fun Article.toEntity() = ArticleEntity(
    publishedAt = publishedAt,
    author = author,
    content = content,
    description = description,
    source = SourceEntity(id = source.id, name = source.name),
    title = title,
    url = url,
    urlToImage = urlToImage,
)
