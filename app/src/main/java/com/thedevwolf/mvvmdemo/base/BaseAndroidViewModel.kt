package com.thedevwolf.mvvmdemo.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.thedevwolf.mvvmdemo.controller.App
import com.thedevwolf.mvvmdemo.di.component.ApiComponent
import com.thedevwolf.mvvmdemo.di.module.ContextModule

import com.thedevwolf.mvvmdemo.vm.activity.MainViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.FavoriteViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.MostPopularViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.TopRatedViewModel

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
            is TopRatedViewModel -> injector.inject(this)
            is MostPopularViewModel -> injector.inject(this)
            is FavoriteViewModel -> injector.inject(this)

        }
    }
}