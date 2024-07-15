package utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


 class AppPreferences(
    private val dataStore: DataStore<Preferences>
)  {

    private companion object {
        private const val IS_DARK_MODE_ENABLED = "prefsBoolean"
    }

    private val darkModeKey = booleanPreferencesKey(IS_DARK_MODE_ENABLED)

    suspend fun isDarkModeEnabled() = dataStore.data.map { preferences ->
        preferences[darkModeKey] ?: false
    }.first()

    suspend fun changeDarkMode(isEnabled : Boolean) = dataStore.edit { preferences ->
        preferences[darkModeKey] = isEnabled
    }

}