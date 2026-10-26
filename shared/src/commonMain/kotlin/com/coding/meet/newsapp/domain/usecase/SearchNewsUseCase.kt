package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository

class SearchNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(query: String): List<Article> =
        repository.searchNews(query)
}
