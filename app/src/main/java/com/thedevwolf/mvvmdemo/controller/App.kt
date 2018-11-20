package com.thedevwolf.mvvmdemo.controller

import android.app.Application
import com.thedevwolf.mvvmdemo.di.component.DaggerApiComponent
import com.thedevwolf.mvvmdemo.di.module.ApiModule
import com.thedevwolf.mvvmdemo.di.module.AppModule

class App:Application() {


    //inject application context for shared preference and more
    var netComponent: DaggerApiComponent.Builder ?= null
        private set
    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule)
            .appModule(AppModule(this))
    }
}