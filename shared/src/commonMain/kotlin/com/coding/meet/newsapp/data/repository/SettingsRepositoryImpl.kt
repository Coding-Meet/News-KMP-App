package com.coding.meet.newsapp.data.repository

import androidx.datastore.preferences.core.Preferences
import com.coding.meet.newsapp.core.preferences.AppPreferences
import com.coding.meet.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val appPreferences: AppPreferences,
) : SettingsRepository {

    override fun getTheme(): Flow<String> = appPreferences.getThemeFlow()

    override suspend fun setTheme(theme: String): Preferences =
        appPreferences.changeThemeMode(theme)
}
