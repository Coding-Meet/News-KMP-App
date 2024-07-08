package com.coding.meet.newsapp

import android.app.Activity
import android.app.Application
import android.content.Context
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MyApp : Application(), KoinComponent {
    companion object{
        var instance : MyApp? = null
        var activity: Activity? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin {
            androidLogger()
            androidContext(this@MyApp)
        }
    }
}
fun getContext(): Context?{
    return MyApp.instance
}
fun getActivity(): Activity?{
    return MyApp.activity
}