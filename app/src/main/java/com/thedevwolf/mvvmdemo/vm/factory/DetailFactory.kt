package com.thedevwolf.mvvmdemo.vm.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.vm.activity.DetailViewModel

class DetailFactory(private val application: Application,private val result: Movie.Result):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(application,result) as T
    }
}