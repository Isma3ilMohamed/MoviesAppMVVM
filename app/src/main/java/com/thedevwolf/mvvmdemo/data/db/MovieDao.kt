package com.thedevwolf.mvvmdemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.thedevwolf.mvvmdemo.data.db.Entity.MovieEntity

@Dao
interface MovieDao {



    @Query("SELECT * FROM data")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM data WHERE id = :id")
    fun getMovieById(id:Int):LiveData<MovieEntity>

}