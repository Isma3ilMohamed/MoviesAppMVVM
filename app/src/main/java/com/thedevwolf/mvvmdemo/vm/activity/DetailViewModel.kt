package com.thedevwolf.mvvmdemo.vm.activity

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import com.orhanobut.logger.Logger

import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel(application: Application,val result: Movie.Result)
    :BaseAndroidViewModel(application) {

    private val compositeDisposable=CompositeDisposable()
    @Inject lateinit var repository: Repository

    val isFavorite by lazy {
        MutableLiveData<Boolean>()
    }

    var favDrawable:LiveData<Int> ?= null

    val movieResult by lazy {
        MutableLiveData<Movie.Result>()
    }

    init {
        loadData()
    }

    private fun loadData() {
        movieResult.value=result

        isMovieFavorite()

    }

    private fun isMovieFavorite() {

        /*
        * Check if movie is favorite or not
        * and set drawable depend on this value
        * */
        isFavorite.value = repository.getFavoriteMovie(result.id!!).value!=null

        favDrawable=Transformations.map(isFavorite){
            if (it) R.drawable.like_red else R.drawable.like
        }
    }

    fun addRemoveMovieToFavorite(movie: Movie.Result){
        if (!isFavorite.value!!){
            addToFavorite(movie)
        }else{
            removeFromFavorite(movie)
        }
    }

    private fun addToFavorite(movie: Movie.Result) {
        compositeDisposable.add(
            Observable.fromCallable {
                repository.insertMovie(movie)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavorite.value=true
                    Logger.e(Gson().toJson(repository.getFavoriteMovie(movie.id!!)))
                },{
                    Logger.e(it.toString())
                })
        )
    }
    private fun removeFromFavorite(movie: Movie.Result) {
        compositeDisposable.add(
            Observable.fromCallable {
                repository.deleteMovie(movie)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavorite.value=false
                    Logger.e(Gson().toJson(repository.getFavoriteMovie(movie.id!!)))
                },{
                    Logger.e(it.toString())
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}