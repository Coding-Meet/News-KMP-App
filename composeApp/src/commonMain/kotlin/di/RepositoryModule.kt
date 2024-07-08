package di

import data.database.NewsDatabase
import data.repository.LocalNewsRepository
import data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
    single {
        OnlineNewsRepository(get())
    }
}