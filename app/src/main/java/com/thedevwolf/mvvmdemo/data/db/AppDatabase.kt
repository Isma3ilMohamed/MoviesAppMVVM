package com.thedevwolf.mvvmdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.data.db.Entity.MovieEntity

@Database(entities = [Movie.Result::class],version = 3)
abstract class AppDatabase: RoomDatabase() {


    abstract fun movieDao():MovieDao


    companion object {
        val DB_NAME="movie.db"
    }
}