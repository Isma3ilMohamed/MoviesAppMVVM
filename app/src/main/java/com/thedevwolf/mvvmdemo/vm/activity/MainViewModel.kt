package com.thedevwolf.mvvmdemo.vm.activity

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseAndroidViewModel
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.ui.fragment.FavoriteFragment
import com.thedevwolf.mvvmdemo.ui.fragment.MostPopularFragment
import com.thedevwolf.mvvmdemo.ui.fragment.TopRatedFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class MainViewModel(application: Application) : BaseAndroidViewModel(application) {
    @Inject
    lateinit var repository: Repository

    private val compositeDisposable = CompositeDisposable()

    //for loaded fragment
    val loadingFragment by lazy {
        MutableLiveData<Fragment>()
    }
    val loadedFragmentTitle by lazy {
        MutableLiveData<String>()
    }


    init {
        loadingFragment.value=TopRatedFragment.newInstance()
        loadedFragmentTitle.value="Top Rated"
    }


     val navigationMenuSelectListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.top_rate -> {
                    Log.e(javaClass.simpleName, "Top Rated")
                    loadingFragment.value=TopRatedFragment.newInstance()
                    loadedFragmentTitle.value="Top Rated"

                    return@OnNavigationItemSelectedListener true
                }
                R.id.most_popular -> {
                    Log.e(javaClass.simpleName, "Most popular")
                    loadingFragment.value=MostPopularFragment.newInstance()
                    loadedFragmentTitle.value="Most Popular"

                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite ->{
                    Log.e(javaClass.simpleName, "Favorite")
                    loadingFragment.value=FavoriteFragment.newInstance()
                    loadedFragmentTitle.value="Favorite"

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