package di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.article_detail.ArticleDetailViewModel
import ui.bookmark.BookmarkViewModel
import ui.headline.HeadlineViewModel
import ui.search.SearchViewModel
import ui.setting.SettingViewModel

actual val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}
