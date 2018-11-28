package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.adapter.FavoriteAdapter
import javax.inject.Inject

class FavoriteViewModel(application: Application) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    val favoriteAdapter = FavoriteAdapter()

    val favoriteEmpty by lazy {
        MutableLiveData<Int>()
    }

    init {
        loadData()
    }

    private fun loadData() {
        Logger.e(Gson().toJson(repository.getFavoriteMovies().value))
        if (repository.getFavoriteMovies().value != null) {
            favoriteEmpty.value = View.GONE
            favoriteAdapter.submitList(repository.getFavoriteMovies().value)
        } else {
            favoriteEmpty.value = View.VISIBLE
        }


    }



}