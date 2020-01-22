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
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.databinding.FragmentFavoriteBinding
import com.thedevwolf.mvvmdemo.ui.adapter.FavoriteAdapter
import com.thedevwolf.mvvmdemo.vm.fragment.FavoriteViewModel

class FavoriteFragment:Fragment() {

    lateinit var favoriteBinding: FragmentFavoriteBinding
    lateinit var favoriteViewModel: FavoriteViewModel
    private val favoriteAdapter = FavoriteAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favoriteBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false)

        return  favoriteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        favoriteViewModel=ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        favoriteBinding.lifecycleOwner = this

        if  (resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){

            favoriteBinding.rvMovies.layoutManager = GridLayoutManager(context, 3)

        }else {
            favoriteBinding.rvMovies.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getAllMovies().observe(this, Observer {
            if (it.isNotEmpty()){
                favoriteAdapter.submitList(it)
                favoriteBinding.rvMovies.adapter=favoriteAdapter
            }else{
                favoriteBinding.tvNoFav.visibility=View.VISIBLE
                favoriteBinding.rvMovies.visibility=View.GONE

            }
        })
    }



    companion object {
        fun newInstance():FavoriteFragment=FavoriteFragment()
    }
}