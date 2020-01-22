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
import com.thedevwolf.mvvmdemo.databinding.FragmentTopRatedBinding
import com.thedevwolf.mvvmdemo.vm.fragment.TopRatedViewModel

class TopRatedFragment : Fragment() {

    lateinit var topRatedBinding: FragmentTopRatedBinding
    lateinit var topRatedViewModel: TopRatedViewModel
    private var errorSnackbar: Snackbar? = null


    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(it: String) {
        errorSnackbar = Snackbar.make(topRatedBinding.root, it, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, topRatedViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        topRatedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false)

        return topRatedBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }
    private fun initFragment() {

        topRatedViewModel=ViewModelProviders.of(this).get(TopRatedViewModel::class.java)

        topRatedBinding.lifecycleOwner = this
        //layout manager

        if  (resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) {

            topRatedBinding.rvMovies.layoutManager = GridLayoutManager(activity, 3)
        }else{
            topRatedBinding.rvMovies.layoutManager = GridLayoutManager(activity, 2)
        }
        topRatedViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
             error -> if(error!=null)showError(error) else hideError()
        })


        topRatedBinding.movie=topRatedViewModel


    }

    companion object {
        fun newInstance(): TopRatedFragment = TopRatedFragment()
    }
}