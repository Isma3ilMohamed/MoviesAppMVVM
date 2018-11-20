package com.thedevwolf.mvvmdemo.data

import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.BuildConfig
import com.thedevwolf.mvvmdemo.data.db.MovieDao
import com.thedevwolf.mvvmdemo.data.network.Api
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api,private val movieDao: MovieDao) {

    fun getPopularMovies(): Observable<Movie> {
        return api.getTopRatedMovies(BuildConfig.API_KEY)
    }
}