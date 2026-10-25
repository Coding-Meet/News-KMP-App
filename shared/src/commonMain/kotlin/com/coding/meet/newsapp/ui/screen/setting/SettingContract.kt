package com.coding.meet.newsapp.ui.screen.setting

data class SettingState(
    val currentTheme: String = "",
    val showDeleteDialog: Boolean = false,
    val showThemeDialog: Boolean = false,
)

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
