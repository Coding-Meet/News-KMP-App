package com.coding.meet.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.coding.meet.newsapp.utils.setActivityProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setActivityProvider { this }
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(0, 0),
                navigationBarStyle = SystemBarStyle.auto(0, 0),
            )
            App()
        }
    }
}