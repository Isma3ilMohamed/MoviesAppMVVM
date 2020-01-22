package com.thedevwolf.mvvmdemo.di.component

import com.thedevwolf.mvvmdemo.di.module.ApiModule
import com.thedevwolf.mvvmdemo.di.module.AppModule
import com.thedevwolf.mvvmdemo.di.module.ContextModule
import com.thedevwolf.mvvmdemo.di.module.DatabaseModule
import com.thedevwolf.mvvmdemo.vm.activity.DetailViewModel
import com.thedevwolf.mvvmdemo.vm.activity.MainViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.FavoriteViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.MostPopularViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.TopRatedViewModel
import dagger.Component

@Component(modules = [AppModule::class, ContextModule::class, ApiModule::class,DatabaseModule::class])
interface ApiComponent {


    fun inject(mainViewModel: MainViewModel)
    fun inject(topRatedViewModel: TopRatedViewModel)
    fun inject(mostPopularViewModel: MostPopularViewModel)
    fun inject(favoriteViewModel: FavoriteViewModel)
    fun inject(detailViewModel: DetailViewModel)
}