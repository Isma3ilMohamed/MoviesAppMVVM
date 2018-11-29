package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.activity.DetailActivity
import com.thedevwolf.mvvmdemo.ui.adapter.MoviesAdapter
import com.thedevwolf.mvvmdemo.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class MostPopularViewModel(application: Application):BaseAndroidViewModel(application) {



    @Inject
    lateinit var repository: Repository

    private val compositeDisposable = CompositeDisposable()
    val loadingVisibility = MutableLiveData<Int>()
    var heroesAdapter = MoviesAdapter {
        application.apply {
            startActivity(intentFor<DetailActivity>().putExtras(Bundle().apply {
                putParcelable(Constants.MOVIE,it)
            }).newTask())
        }
    }

    //error control
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTopRatedMovie() }

    init {
        loadTopRatedMovie()
    }

    private fun loadTopRatedMovie() {
        compositeDisposable.add(
            repository.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loadingVisibility.value = View.VISIBLE
                    errorMessage.value = null
                }
                .doOnTerminate {
                    loadingVisibility.value = View.GONE
                }
                .subscribe({
                    heroesAdapter.addHeros(it.results)
                }, {
                    errorMessage.value = "error while fetching data"

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}