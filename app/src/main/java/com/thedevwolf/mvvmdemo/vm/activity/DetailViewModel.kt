package com.thedevwolf.mvvmdemo.vm.activity

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations


import com.thedevwolf.mvvmdemo.data.model.Movie
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository


import javax.inject.Inject

class DetailViewModel( application: Application,val result: Movie.Result)
    :BaseAndroidViewModel(application) {

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
        //repository.getAllMovies()
        isMovieFavorite()

    }

    private fun isMovieFavorite() {

        /*
        * Check if movie is favorite or not
        * and set drawable depend on this value
        * */

        //init value
        isFavorite.value=false

        favDrawable=Transformations.map(isFavorite){
            if (it) R.drawable.like_red else R.drawable.like
        }
    }

    fun addRemoveMovieToFavorite(movieEntity: Movie.Result){
        when (isFavorite.value){
            false -> {
                repository.insertMovie(movieEntity)
                isFavorite.value = true
            }
            true -> {
                repository.deleteMovie(movieEntity)
                isFavorite.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }


}