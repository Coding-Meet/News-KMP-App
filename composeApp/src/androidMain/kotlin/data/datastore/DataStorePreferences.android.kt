package data.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.mp.KoinPlatform
import utils.dataStoreFileName

actual fun dataStorePreferences(): DataStore<Preferences> {
    val appContext = KoinPlatform.getKoin().get<Application>()
     return AppSettings.getDataStore(
        producePath = {
            appContext.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}