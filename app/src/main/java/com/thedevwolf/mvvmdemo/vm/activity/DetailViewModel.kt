package com.thedevwolf.mvvmdemo.vm.activity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel

class DetailViewModel(application: Application,val result: Movie.Result)
    :BaseAndroidViewModel(application) {

    val movieResult by lazy {
        MutableLiveData<Movie.Result>()
    }

    init {
        loadData()
    }

    private fun loadData() {
        movieResult.value=result

    }


}