package com.coding.meet.newsapp.core.preferences.di

import com.coding.meet.newsapp.core.preferences.AppPreferences
import com.coding.meet.newsapp.data.datastore.dataStorePreferences
import org.koin.dsl.module

val preferencesModule = module {
    single { AppPreferences(dataStorePreferences()) }
}
