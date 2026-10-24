package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.repository.NewsRepository

class ClearBookmarksUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.clearAllBookmarks()
}
