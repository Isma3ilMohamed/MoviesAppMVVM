package com.thedevwolf.mvvmdemo.vm

import androidx.lifecycle.MutableLiveData
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.base.BaseViewModel
import com.thedevwolf.mvvmdemo.utils.Constants

class HeroViewModel:BaseViewModel() {
    val movieTitle=MutableLiveData<String>()
    val movieImage=MutableLiveData<String>()

    fun bind(hero: Movie.Result){
        movieTitle.value=hero.title
        movieImage.value= Constants.BASE_POSTER_URL + hero.poster_path
    }
}