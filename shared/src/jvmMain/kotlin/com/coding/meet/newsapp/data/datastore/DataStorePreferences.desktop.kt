package com.coding.meet.newsapp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.coding.meet.newsapp.utils.dataStoreFileName

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}