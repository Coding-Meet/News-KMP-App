package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.ui.screen.article_detail.ArticleDetailViewModel
import com.coding.meet.newsapp.ui.screen.bookmark.BookmarkViewModel
import com.coding.meet.newsapp.ui.screen.headline.HeadlineViewModel
import com.coding.meet.newsapp.ui.screen.search.SearchViewModel
import com.coding.meet.newsapp.ui.screen.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::ArticleDetailViewModel)
}
