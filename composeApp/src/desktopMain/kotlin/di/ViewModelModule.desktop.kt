package di

import org.koin.dsl.module
import ui.headline.HeadlineViewModel
import ui.search.SearchViewModel
import ui.bookmark.BookmarkViewModel
import ui.setting.SettingViewModel
import ui.article_detail.ArticleDetailViewModel

actual val viewmodelModule = module {
    factory {
        HeadlineViewModel(get())
    }
    factory {
        SearchViewModel(get())
    }
    factory {
        BookmarkViewModel(get())
    }
    factory {
        ArticleDetailViewModel(get())
    }
    factory {
        SettingViewModel(get(),get())
    }
}