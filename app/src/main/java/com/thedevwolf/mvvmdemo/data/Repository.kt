package com.thedevwolf.mvvmdemo.data

import androidx.lifecycle.LiveData
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.BuildConfig
import com.thedevwolf.mvvmdemo.data.db.Entity.MovieEntity
import com.thedevwolf.mvvmdemo.data.db.MovieDao
import com.thedevwolf.mvvmdemo.data.network.Api
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api,private val movieDao: MovieDao) {


    //API
    fun getPopularMovies(): Observable<Movie> {
        return api.getMostPopularMovies(BuildConfig.API_KEY)
    }

    fun getTopRated():Observable<Movie>{
        return api.getTopRatedMovies(BuildConfig.API_KEY)
    }



    //Database
    fun getFavoriteMovies():LiveData<List<MovieEntity>>{

        return movieDao.getAllMovie()
    }

    fun getFavoriteMovie(id:Int):LiveData<MovieEntity>{
        return movieDao.getMovieById(id)
    }

}