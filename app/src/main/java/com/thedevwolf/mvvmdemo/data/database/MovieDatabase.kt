package com.thedevwolf.mvvmdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thedevwolf.mvvmdemo.data.model.Movie

@Database(entities = [Movie.Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}