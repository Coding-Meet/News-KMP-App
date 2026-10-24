package com.coding.meet.newsapp

import androidx.compose.ui.window.ComposeUIViewController
import com.coding.meet.newsapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}