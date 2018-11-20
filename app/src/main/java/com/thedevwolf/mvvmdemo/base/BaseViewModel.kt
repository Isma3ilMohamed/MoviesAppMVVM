package com.thedevwolf.mvvmdemo.base

import androidx.lifecycle.ViewModel
import com.thedevwolf.mvvmdemo.di.component.DaggerApiComponent
import com.thedevwolf.mvvmdemo.di.module.ApiModule

abstract class BaseViewModel:ViewModel() {

    /*private val injector=DaggerApiComponent.builder().apiModule(ApiModule)
        .build()
    init {
        inject()
    }

    private fun inject() {

    }*/
}