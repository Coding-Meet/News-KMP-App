package com.coding.meet.newsapp.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getTheme(): Flow<String>
    suspend fun setTheme(theme: String): Preferences
}
