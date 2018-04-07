package com.hod.themoviedb

import android.app.Application
import com.hod.themoviedb.di.AppComponent
import com.hod.themoviedb.di.DaggerAppComponent

class App: Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }
}