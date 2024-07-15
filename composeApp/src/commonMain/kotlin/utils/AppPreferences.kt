package utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {

    private val themeKey = stringPreferencesKey("theme")

    suspend fun getTheme() = dataStore.data.map { preferences ->
        preferences[themeKey] ?: Theme.DARK_MODE.name
    }.first()

    suspend fun changeThemeMode(value: String) = dataStore.edit { preferences ->
        preferences[themeKey] = value
    }

}