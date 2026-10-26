package com.coding.meet.newsapp.data.remote.service

import com.coding.meet.newsapp.BuildKonfig.API_KEY
import com.coding.meet.newsapp.data.remote.dto.ArticleDto
import com.coding.meet.newsapp.data.remote.dto.ErrorResponseDto
import com.coding.meet.newsapp.data.remote.dto.NewsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApiService(private val client: HttpClient) {

    suspend fun getHeadlines(category: String): List<ArticleDto> = fetchArticles(category)

    suspend fun searchNews(query: String): List<ArticleDto> = fetchArticles(query)

    private suspend fun fetchArticles(query: String): List<ArticleDto> {
        val response = client.get("everything") {
            parameter("q", query)
            parameter("apiKey", API_KEY)
        }
        return if (response.status.value in 200..299) {
            response.body<NewsResponseDto>().articles
        } else {
            val error = response.body<ErrorResponseDto>()
            throw Exception(error.message)
        }
    }
}
