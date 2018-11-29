package com.thedevwolf.mvvmdemo.data.database

import androidx.room.*
import com.thedevwolf.mvvmdemo.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies():Single<List<Movie.Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie.Result)


    @Delete
    fun deleteMovie(movie: Movie.Result)

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id:Int):Single<Movie.Result>
}