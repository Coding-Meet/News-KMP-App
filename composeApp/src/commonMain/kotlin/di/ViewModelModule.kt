package di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ui.article_detail.ArticleDetailViewModel
import ui.bookmark.BookmarkViewModel
import ui.headline.HeadlineViewModel
import ui.search.SearchViewModel
import ui.setting.SettingViewModel

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}