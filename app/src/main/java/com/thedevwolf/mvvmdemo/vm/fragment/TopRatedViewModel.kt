package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.activity.DetailActivity
import com.thedevwolf.mvvmdemo.ui.adapter.HeroesAdapter
import com.thedevwolf.mvvmdemo.utils.Constants.MOVIE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class TopRatedViewModel(application: Application) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    private val compositeDisposable = CompositeDisposable()
    val loadingVisibility = MutableLiveData<Int>()
    var heroesAdapter = HeroesAdapter{

        application.apply {
            startActivity(intentFor<DetailActivity>().putExtras(Bundle().apply {
                putParcelable(MOVIE,it)
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
            repository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loadingVisibility.value = View.VISIBLE
                    errorMessage.value = null
                    Log.e(javaClass.simpleName,"loading")
                }
                .doOnTerminate {
                    loadingVisibility.value = View.GONE

                    Log.e(javaClass.simpleName,"loaded")
                }
                .subscribe({
                    heroesAdapter.addHeros(it.results)

                    Log.e(javaClass.simpleName,"success")
                }, {

                    Log.e(javaClass.simpleName,"failed")
                    errorMessage.value = "error while fetching data"

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}