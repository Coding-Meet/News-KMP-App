package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ui.headline.HeadlineViewModel
import ui.search.SearchViewModel
import ui.bookmark.BookmarkViewModel
import ui.setting.SettingViewModel
import ui.article_detail.ArticleDetailViewModel

actual val viewmodelModule = module {
    singleOf(::HeadlineViewModel)
    singleOf(::SearchViewModel)
    singleOf(::BookmarkViewModel)
    singleOf(::ArticleDetailViewModel)
    singleOf(::SettingViewModel)
}