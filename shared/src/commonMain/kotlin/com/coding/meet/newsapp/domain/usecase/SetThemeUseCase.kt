package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.repository.SettingsRepository

class SetThemeUseCase(private val repository: SettingsRepository) {
    suspend operator fun invoke(theme: String) = repository.setTheme(theme)
}
