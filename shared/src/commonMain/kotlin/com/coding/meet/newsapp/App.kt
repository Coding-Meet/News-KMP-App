package com.coding.meet.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.MainScreen
import com.coding.meet.newsapp.ui.setting.SettingViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val settingViewModel = koinViewModel<SettingViewModel>()
    val state by settingViewModel.state.collectAsStateWithLifecycle()
    NewsAppTheme(appTheme = state.currentTheme.ifBlank { null }) {
        MainScreen()
    }
}
