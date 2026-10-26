package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository

class GetArticleByIdUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(publishedAt: String): Article? =
        repository.getArticleById(publishedAt)
}
