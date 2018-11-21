package com.thedevwolf.mvvmdemo.vm.fragment

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.adapter.HeroesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedViewModel(application: Application) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    private val compositeDisposable = CompositeDisposable()
    val loadingVisibility = MutableLiveData<Int>()
    var heroesAdapter = HeroesAdapter()

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