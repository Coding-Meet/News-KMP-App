package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository

class RemoveBookmarkUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(article: Article) = repository.removeBookmark(article)
}
