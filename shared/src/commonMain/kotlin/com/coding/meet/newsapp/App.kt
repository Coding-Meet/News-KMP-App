package com.coding.meet.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.ui.MainScreen
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.screen.setting.SettingViewModel
import com.coding.meet.newsapp.utils.Theme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(systemAppearance: @Composable (isDark: Boolean) -> Unit = {}) {
    val settingViewModel = koinViewModel<SettingViewModel>()
    val state by settingViewModel.state.collectAsStateWithLifecycle()
    NewsAppTheme(
        appTheme = state.currentThemeName.ifBlank { null },
        systemAppearance = systemAppearance,
    ) {
        MainScreen()
    }
}
