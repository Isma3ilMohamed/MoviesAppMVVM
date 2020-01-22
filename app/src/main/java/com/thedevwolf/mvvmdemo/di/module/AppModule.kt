package com.thedevwolf.mvvmdemo.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal class AppModule(private val mApplication: Application) {

    @Provides
    @Reusable
    fun provideApplication(): Application {
        return mApplication
    }


}