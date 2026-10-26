package com.coding.meet.newsapp.domain.usecase

import com.coding.meet.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetThemeUseCase(private val repository: SettingsRepository) {
    operator fun invoke(): Flow<String> = repository.getTheme()
}
