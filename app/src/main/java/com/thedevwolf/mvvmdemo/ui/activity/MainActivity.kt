package com.thedevwolf.mvvmdemo.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseActivity
import com.thedevwolf.mvvmdemo.databinding.ActivityMainBinding
import com.thedevwolf.mvvmdemo.vm.activity.MainViewModel

class MainActivity : BaseActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun init(savedInstanceState: Bundle?) {
        mainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

       mainViewModel.loadingFragment.observe(this, Observer {
           openFragment(it)
       })
        mainViewModel.loadedFragmentTitle.observe(this, Observer {
            supportActionBar!!.apply {
                title=it
            }
        })

        mainBinding.heros=mainViewModel

    }
    private fun openFragment(fragment: Fragment){
        val transition=supportFragmentManager.beginTransaction()
        transition.replace(R.id.container,fragment)
        transition.addToBackStack(null)
        transition.commit()
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
