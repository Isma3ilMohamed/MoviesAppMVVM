package com.thedevwolf.mvvmdemo.di.module

import android.content.Context
import androidx.room.Room
import com.thedevwolf.mvvmdemo.data.db.Appdatabase
import com.thedevwolf.mvvmdemo.data.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RoomModule {


    @Provides
    @Reusable
    fun provideMovieDb(context: Context):Appdatabase{
        return  Room.databaseBuilder(context,Appdatabase::class.java,Appdatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideMovieDao(appDatabase: Appdatabase):MovieDao{
        return appDatabase.movieDao()
    }

}