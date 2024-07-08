package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.NewsDatabase
import data.datastore.dataStorePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import utils.AppPreferencesImpl
import utils.getDatabaseBuilder

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    // datastore
    single {
        AppPreferencesImpl(dataStorePreferences())
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
