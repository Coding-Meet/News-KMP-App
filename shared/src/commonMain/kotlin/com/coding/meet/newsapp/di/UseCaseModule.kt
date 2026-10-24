package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.domain.usecase.BookmarkArticleUseCase
import com.coding.meet.newsapp.domain.usecase.ClearBookmarksUseCase
import com.coding.meet.newsapp.domain.usecase.GetArticleByIdUseCase
import com.coding.meet.newsapp.domain.usecase.GetBookmarksUseCase
import com.coding.meet.newsapp.domain.usecase.GetHeadlinesUseCase
import com.coding.meet.newsapp.domain.usecase.GetThemeUseCase
import com.coding.meet.newsapp.domain.usecase.IsArticleBookmarkedUseCase
import com.coding.meet.newsapp.domain.usecase.RemoveBookmarkUseCase
import com.coding.meet.newsapp.domain.usecase.SearchNewsUseCase
import com.coding.meet.newsapp.domain.usecase.SetThemeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetHeadlinesUseCase)
    factoryOf(::SearchNewsUseCase)
    factoryOf(::GetBookmarksUseCase)
    factoryOf(::GetArticleByIdUseCase)
    factoryOf(::BookmarkArticleUseCase)
    factoryOf(::RemoveBookmarkUseCase)
    factoryOf(::IsArticleBookmarkedUseCase)
    factoryOf(::ClearBookmarksUseCase)
    factoryOf(::GetThemeUseCase)
    factoryOf(::SetThemeUseCase)
}
