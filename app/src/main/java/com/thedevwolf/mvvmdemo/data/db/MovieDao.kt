package com.thedevwolf.mvvmdemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thedevwolf.moviesappmvvm.data.model.Movie

@Dao
interface MovieDao {



    @Query("SELECT * FROM result")
    fun getAllMovie(): LiveData<List<Movie.Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    fun insertMovie(movie: Movie.Result)

    @Delete
    fun deleteMovie(movie: Movie.Result)

    @Query("SELECT * FROM result WHERE id = :id")
    fun getMovieById(id:Int):LiveData<Movie.Result>

}