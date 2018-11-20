package com.thedevwolf.mvvmdemo.data.network


import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.data.model.Models.Hero
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {


    @GET("popular")
    fun getTopRatedMovies(@Query("api_key") API:String):Observable<Movie>
}