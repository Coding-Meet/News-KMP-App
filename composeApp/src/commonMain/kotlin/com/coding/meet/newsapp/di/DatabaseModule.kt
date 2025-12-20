package com.coding.meet.newsapp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.coding.meet.newsapp.data.database.NewsDatabase
import com.coding.meet.newsapp.data.datastore.dataStorePreferences
import com.coding.meet.newsapp.utils.AppPreferences
import com.coding.meet.newsapp.utils.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }
    single {
        get<NewsDatabase>().newsDao()
    }

    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
