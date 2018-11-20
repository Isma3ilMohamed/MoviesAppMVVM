package com.thedevwolf.mvvmdemo.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseActivity
import com.thedevwolf.mvvmdemo.databinding.ActivityMainBinding
import com.thedevwolf.mvvmdemo.vm.MainViewModel

class MainActivity : BaseActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    override fun init(savedInstanceState: Bundle?) {
        mainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainBinding.rvMovies.layoutManager= GridLayoutManager(this,2)
        mainViewModel.errorMessage.observe(this, Observer {
            error -> if (error!=null) showError(error) else HideError()
        })

       /*mainViewModel.loadingFragment.observe(this, Observer {
           supportFragmentManager.beginTransaction()
               .replace(mainBinding.container.id,it)
               .commit()
       })*/

        mainBinding.heros=mainViewModel

    }

    private fun HideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(it: String) {
        errorSnackbar = Snackbar.make(mainBinding.root, it, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, mainViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun fullScreen(): Boolean {
        return false
    }

    override fun hideInputType(): Boolean {
        return false
    }




}
