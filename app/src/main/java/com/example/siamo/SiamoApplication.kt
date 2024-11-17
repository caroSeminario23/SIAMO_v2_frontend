package com.example.siamo

import android.app.Application
import com.example.siamo.data.AppContainer
import com.example.siamo.data.DefaultAppContainer

class SiamoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}