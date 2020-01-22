package com.thedevwolf.mvvmdemo.vm.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thedevwolf.mvvmdemo.data.model.Movie
import com.thedevwolf.mvvmdemo.utils.Constants

class MovieViewModel:ViewModel() {
    val movieTitle=MutableLiveData<String>()
    val movieImage=MutableLiveData<String>()

    fun bind(hero: Movie.Result){
        movieTitle.value=hero.title
        movieImage.value= Constants.BASE_POSTER_URL + hero.poster_path
    }


}