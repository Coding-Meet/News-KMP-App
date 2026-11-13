package com.coding.meet.newsapp.screenshot

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.utils.previewArticles

internal val sampleArticles: List<Article> = previewArticles

internal val sampleSelectedArticle: Article = previewArticles.first()

internal val sampleSearchArticles: List<Article> = previewArticles.take(3)

internal val sampleBookmarkedArticles: List<Article> = previewArticles.take(2)
