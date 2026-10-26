package com.coding.meet.newsapp.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(
    val dataStore: DataStore<Preferences>
) {
    val themeKey = stringPreferencesKey("com/coding/meet/newsapp/theme")

    fun getThemeFlow(): Flow<String> = dataStore.data.map { preferences ->
        preferences[themeKey] ?: com.coding.meet.newsapp.utils.Theme.DARK_MODE.name
    }

    suspend fun changeThemeMode(value: String) = dataStore.edit { preferences ->
        preferences[themeKey] = value
    }
}
