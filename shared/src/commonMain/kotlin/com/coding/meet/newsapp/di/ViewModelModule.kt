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
    // ArticleDetailViewModel requires articleId passed at runtime via parametersOf(articleId)
    viewModel { params ->
        ArticleDetailViewModel(
            articleId = params.get(),
            articleCache = get(),
            getArticleById = get(),
            isArticleBookmarked = get(),
            bookmarkArticle = get(),
            removeBookmark = get(),
        )
    }
}
