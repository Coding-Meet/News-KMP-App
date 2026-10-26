package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.data.remote.service.NewsApiService
import com.coding.meet.newsapp.data.repository.NewsRepositoryImpl
import com.coding.meet.newsapp.data.repository.SettingsRepositoryImpl
import com.coding.meet.newsapp.domain.repository.NewsRepository
import com.coding.meet.newsapp.domain.repository.SettingsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NewsApiService)
    singleOf(::NewsRepositoryImpl) bind NewsRepository::class
    singleOf(::SettingsRepositoryImpl) bind SettingsRepository::class
}
