package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarksUseCase(private val repository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> = repository.getBookmarks()
}
