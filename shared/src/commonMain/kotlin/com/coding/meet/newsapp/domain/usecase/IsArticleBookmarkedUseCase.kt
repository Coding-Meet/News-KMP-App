package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.repository.NewsRepository

class IsArticleBookmarkedUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(publishedAt: String): Boolean =
        repository.isArticleBookmarked(publishedAt)
}
