package com.coding.meet.newsapp.domain.repository

import com.coding.meet.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getHeadlines(category: String): List<Article>
    suspend fun searchNews(query: String): List<Article>
    fun getBookmarks(): Flow<List<Article>>
    suspend fun getArticleById(publishedAt: String): Article?
    suspend fun bookmarkArticle(article: Article)
    suspend fun removeBookmark(article: Article)
    suspend fun isArticleBookmarked(publishedAt: String): Boolean
    suspend fun clearAllBookmarks()
}
