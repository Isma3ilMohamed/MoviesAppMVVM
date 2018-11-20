package com.thedevwolf.mvvmdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thedevwolf.mvvmdemo.data.db.Entity.MovieEntity

@Database(entities = [MovieEntity::class],version = 1,exportSchema = false)
abstract class Appdatabase: RoomDatabase() {


    abstract fun movieDao():MovieDao


    companion object {
        val DB_NAME="movie.db"
    }
}