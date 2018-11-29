package com.thedevwolf.mvvmdemo.di.module

import android.app.Application
import androidx.room.Room
import com.thedevwolf.mvvmdemo.data.database.MovieDao
import com.thedevwolf.mvvmdemo.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
class DatabaseModule {
    @Reusable
    @Provides
    fun provideMyDatabase(context: Application): MovieDatabase {
        return Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "m-db")
            .build()
    }

    @Reusable
    @Provides
    fun provideUserDao(myDatabase: MovieDatabase): MovieDao {
        return myDatabase.movieDao()
    }
}
