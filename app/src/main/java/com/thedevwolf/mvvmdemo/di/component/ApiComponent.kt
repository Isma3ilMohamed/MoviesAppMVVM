package com.thedevwolf.mvvmdemo.di.component

import com.thedevwolf.mvvmdemo.di.module.ApiModule
import com.thedevwolf.mvvmdemo.di.module.AppModule
import com.thedevwolf.mvvmdemo.di.module.ContextModule
import com.thedevwolf.mvvmdemo.base.BaseActivity
import com.thedevwolf.mvvmdemo.di.module.RoomModule
import com.thedevwolf.mvvmdemo.vm.MainViewModel
import dagger.Component

@Component(modules = [AppModule::class, ContextModule::class, ApiModule::class,RoomModule::class])
interface ApiComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainViewModel: MainViewModel)
}