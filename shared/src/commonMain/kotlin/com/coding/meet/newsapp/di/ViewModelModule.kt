package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.ui.article_detail.ArticleDetailViewModel
import com.coding.meet.newsapp.ui.bookmark.BookmarkViewModel
import com.coding.meet.newsapp.ui.headline.HeadlineViewModel
import com.coding.meet.newsapp.ui.search.SearchViewModel
import com.coding.meet.newsapp.ui.setting.SettingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::SettingViewModel)
    // ArticleDetailViewModel requires article passed at runtime via parametersOf(article)
    viewModel { params ->
        ArticleDetailViewModel(
            article = params.get(),
            isArticleBookmarked = get(),
            bookmarkArticle = get(),
            removeBookmark = get(),
        )
    }
}
