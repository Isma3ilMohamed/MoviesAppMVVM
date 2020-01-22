package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application

import androidx.lifecycle.LiveData

import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.data.model.Movie

import javax.inject.Inject

class FavoriteViewModel(application: Application) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository



    fun getAllMovies():LiveData<List<Movie.Result>>{
        return repository.getLiveDataMovies()
    }





}