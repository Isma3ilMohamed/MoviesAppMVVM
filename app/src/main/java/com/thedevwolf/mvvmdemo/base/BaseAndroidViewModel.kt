package com.thedevwolf.mvvmdemo.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.thedevwolf.mvvmdemo.controller.App
import com.thedevwolf.mvvmdemo.di.component.ApiComponent
import com.thedevwolf.mvvmdemo.di.module.ContextModule

import com.thedevwolf.mvvmdemo.vm.MainViewModel

abstract class BaseAndroidViewModel(application: Application):AndroidViewModel(application) {


    private val injector: ApiComponent = (application as App).netComponent!!.
        contextModule(ContextModule(application.applicationContext))
        .build()



    init {
        inject()
    }

    private fun inject() {
        when(this){
            is MainViewModel -> injector.inject(this)
        }
    }
}