package com.coding.meet.newsapp.di

import com.coding.meet.newsapp.core.network.di.networkModule
import com.coding.meet.newsapp.core.preferences.di.preferencesModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            preferencesModule,
            databaseModule,
            repositoryModule,
            useCaseModule,
            viewmodelModule,
        )
    }
