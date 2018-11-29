package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.data.model.Movie
import com.thedevwolf.mvvmdemo.ui.activity.DetailActivity
import com.thedevwolf.mvvmdemo.ui.adapter.FavoriteAdapter
import com.thedevwolf.mvvmdemo.ui.adapter.MoviesAdapter
import com.thedevwolf.mvvmdemo.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class FavoriteViewModel(application: Application) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    val moviesList:MutableLiveData<List<Movie.Result>> = MutableLiveData()
    val favoriteAdapter = FavoriteAdapter()

    val favoriteEmpty by lazy {
        MutableLiveData<Int>()
    }

    init {
        loadData()
    }

    private fun loadData() {

        repository.compositeDisposable
            .add(
                repository.getAllMovies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        favoriteAdapter.submitList(it)
                    },{

                    })
            )

    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }



}