package com.thedevwolf.mvvmdemo

import android.app.Application
import com.thedevwolf.mvvmdemo.di.component.DaggerApiComponent
import com.thedevwolf.mvvmdemo.di.module.ApiModule
import com.thedevwolf.mvvmdemo.di.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger



class App:Application() {


    //inject application context for shared preference and more
    var netComponent: DaggerApiComponent.Builder ?= null
        private set
    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())


        netComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule)
            .appModule(AppModule(this))
    }
}