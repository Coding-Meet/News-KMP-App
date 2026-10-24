package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.repository.NewsRepository

class BookmarkArticleUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(article: Article) = repository.bookmarkArticle(article)
}
