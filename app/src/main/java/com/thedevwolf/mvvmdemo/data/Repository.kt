package com.thedevwolf.mvvmdemo.data

import androidx.lifecycle.LiveData

import com.thedevwolf.mvvmdemo.data.model.Movie
import com.thedevwolf.mvvmdemo.BuildConfig
import com.thedevwolf.mvvmdemo.data.database.MovieDao

import com.thedevwolf.mvvmdemo.data.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class Repository @Inject constructor(private val api: Api, private val movieDao: MovieDao) {
    val compositeDisposable = CompositeDisposable()


    //API
    fun getPopularMovies(): Observable<Movie> {
        return api.getMostPopularMovies(BuildConfig.API_KEY)
    }

    fun getTopRated(): Observable<Movie> {
        return api.getTopRatedMovies(BuildConfig.API_KEY)
    }



    fun insertMovie(movie: Movie.Result) {
        compositeDisposable.add(
            Observable.fromCallable {
                movieDao.insertMovie(movie)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun deleteMovie(movie: Movie.Result){
        compositeDisposable.add(
            Observable.fromCallable {
                movieDao.deleteMovie(movie)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }
    fun getLiveDataMovies():LiveData<List<Movie.Result>>{
        return movieDao.getMovies()
    }
    fun getLiveDataMovie(id:Int):LiveData<Movie.Result>{
        return movieDao.getLiveDataMovie(id)
    }

    fun clear() {
        compositeDisposable.dispose()
    }

}