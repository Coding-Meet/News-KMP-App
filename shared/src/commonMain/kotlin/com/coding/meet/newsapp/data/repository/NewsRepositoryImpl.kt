package com.coding.meet.newsapp.data.repository

import com.coding.meet.newsapp.core.common.result.getOrThrow
import com.coding.meet.newsapp.core.network.safecall.safeApiCall
import com.coding.meet.newsapp.data.database.NewsDao
import com.coding.meet.newsapp.data.mapper.toDomain
import com.coding.meet.newsapp.data.mapper.toEntity
import com.coding.meet.newsapp.data.remote.service.NewsApiService
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getHeadlines(category: String): List<Article> =
        safeApiCall { apiService.getHeadlines(category).map { it.toDomain() } }.getOrThrow()

    override suspend fun searchNews(query: String): List<Article> =
        safeApiCall { apiService.searchNews(query).map { it.toDomain() } }.getOrThrow()

    override fun getBookmarks(): Flow<List<Article>> =
        newsDao.getArticles().map { list -> list.map { it.toDomain() } }

    override suspend fun getArticleById(publishedAt: String): Article? =
        newsDao.getArticle(publishedAt)?.toDomain()

    override suspend fun bookmarkArticle(article: Article) =
        newsDao.upsert(article.toEntity())

    override suspend fun removeBookmark(article: Article) =
        newsDao.delete(article.toEntity())

    override suspend fun isArticleBookmarked(publishedAt: String): Boolean =
        newsDao.getArticle(publishedAt) != null

    override suspend fun clearAllBookmarks() = newsDao.deleteAllBookmark()
}
