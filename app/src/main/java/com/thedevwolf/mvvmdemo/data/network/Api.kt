package com.thedevwolf.mvvmdemo.data.network


import com.thedevwolf.mvvmdemo.data.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {


    @GET("top_rated")
    fun getTopRatedMovies(@Query("api_key") API: String): Observable<Movie>

    @GET("popular")
    fun getMostPopularMovies(@Query("api_key") API: String): Observable<Movie>
}