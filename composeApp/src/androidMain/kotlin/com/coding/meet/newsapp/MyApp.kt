package com.coding.meet.newsapp

import android.app.Activity
import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object{
        var instance : MyApp? = null
        var activity: Activity? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
fun getContext(): Context?{
    return MyApp.instance
}
fun getActivity(): Activity?{
    return MyApp.activity
}