package com.thedevwolf.mvvmdemo.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseFragment
import com.thedevwolf.mvvmdemo.databinding.FragmentMostPopularBinding
import com.thedevwolf.mvvmdemo.vm.fragment.MostPopularViewModel
import com.thedevwolf.mvvmdemo.vm.fragment.TopRatedViewModel

class MostPopularFragment:Fragment() {

    lateinit var mostPopularBinding: FragmentMostPopularBinding
    lateinit var mostPopularViewModel: MostPopularViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mostPopularBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_most_popular,container,false)


        return mostPopularBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment()

    }


    private fun initFragment() {

        mostPopularViewModel= ViewModelProviders.of(this).get(MostPopularViewModel::class.java)
        mostPopularBinding.setLifecycleOwner(this)
        //layout manager

        if  (resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            mostPopularBinding.rvMovies.layoutManager = GridLayoutManager(activity, 3)
        }else{
            mostPopularBinding.rvMovies.layoutManager = GridLayoutManager(activity, 2)

        }
        mostPopularViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
                error -> if(error!=null)showError(error) else hideError()
        })


        mostPopularBinding.movie=mostPopularViewModel


    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(it: String) {
        errorSnackbar = Snackbar.make(mostPopularBinding.root, it, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, mostPopularViewModel.errorClickListener)
        errorSnackbar?.show()
    }


    companion object {
        fun newInstance():MostPopularFragment=MostPopularFragment()
    }
}