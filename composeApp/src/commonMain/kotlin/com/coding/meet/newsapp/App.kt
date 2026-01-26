package com.coding.meet.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.coding.meet.newsapp.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.MainScreen
import com.coding.meet.newsapp.ui.setting.SettingViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val settingViewModel = koinViewModel<SettingViewModel>()
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    NewsAppTheme(currentTheme) {
        MainScreen(settingViewModel)
    }
}