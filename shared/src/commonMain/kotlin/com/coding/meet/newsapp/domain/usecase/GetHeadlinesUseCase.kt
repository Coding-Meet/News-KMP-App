package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository

class GetHeadlinesUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(category: String): List<Article> =
        repository.getHeadlines(category)
}
