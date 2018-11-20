package com.thedevwolf.mvvmdemo.vm

import android.app.Application
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.adapter.HeroesAdapter
import com.thedevwolf.mvvmdemo.ui.fragment.FavoriteFragment
import com.thedevwolf.mvvmdemo.ui.fragment.MostPopularFragment
import com.thedevwolf.mvvmdemo.ui.fragment.TopRatedFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel(application: Application) : BaseAndroidViewModel(application) {
    @Inject
    lateinit var repository: Repository

    private val compositeDisposable = CompositeDisposable()

    //for loaded fragment
    val loadingFragment = MutableLiveData<Fragment>()


    //for progress bar
    val loadingVisibility = MutableLiveData<Int>()
    //for adapter
    var heroesAdapter = HeroesAdapter()

    //error control
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadHeros() }

    init {
        //loadingFragment.value=TopRatedFragment.newInstance()

        loadHeros()
    }

    private fun loadHeros() {
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


     val navigationMenuSelectListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.top_rate -> {
                    Log.e(javaClass.simpleName, "Top Rated")
                    loadingFragment.value=TopRatedFragment.newInstance()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.most_popular -> {
                    Log.e(javaClass.simpleName, "Most popular")
                    loadingFragment.value=MostPopularFragment.newInstance()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite ->{
                    Log.e(javaClass.simpleName, "Favorite")
                    loadingFragment.value=FavoriteFragment.newInstance()
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }

        }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}