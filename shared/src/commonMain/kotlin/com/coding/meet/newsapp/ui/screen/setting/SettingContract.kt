package com.coding.meet.newsapp.ui.screen.setting

import com.coding.meet.newsapp.utils.Theme

data class SettingState(
    val currentThemeName: String = "",
    val showDeleteDialog: Boolean = false,
    val showThemeDialog: Boolean = false,
) {
    val currentTheme: Theme
        get() = try {
            if (currentThemeName.isBlank()) Theme.SYSTEM_DEFAULT else Theme.valueOf(currentThemeName)
        } catch (_: Exception) {
            Theme.SYSTEM_DEFAULT
        }
}

sealed interface SettingEvent {
    data class ChangeTheme(val theme: String) : SettingEvent
    data object ShowThemeDialog : SettingEvent
    data object DismissThemeDialog : SettingEvent
    data object ShowDeleteDialog : SettingEvent
    data object DismissDeleteDialog : SettingEvent
    data object ConfirmDeleteHistory : SettingEvent
    data object NavigateBack : SettingEvent
}

sealed interface SettingEffect {
    data object NavigateBack : SettingEffect
}
