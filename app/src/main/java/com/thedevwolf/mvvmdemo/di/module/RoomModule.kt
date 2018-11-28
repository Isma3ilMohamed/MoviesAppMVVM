package com.thedevwolf.mvvmdemo.di.module

import android.content.Context
import androidx.room.Room
import com.thedevwolf.mvvmdemo.data.db.AppDatabase
import com.thedevwolf.mvvmdemo.data.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RoomModule {


    @Provides
    @Reusable
    fun provideMovieDb(context: Context):AppDatabase{
        return  Room.databaseBuilder(context,AppDatabase::class.java,AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideMovieDao(appDatabase: AppDatabase):MovieDao{
        return appDatabase.movieDao()
    }

}