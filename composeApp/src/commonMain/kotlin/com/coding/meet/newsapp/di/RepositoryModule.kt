package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.data.repository.LocalNewsRepository
import com.coding.meet.newsapp.data.repository.OnlineNewsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::LocalNewsRepository)
    singleOf(::OnlineNewsRepository)
}