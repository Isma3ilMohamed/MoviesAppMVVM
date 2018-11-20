package com.thedevwolf.mvvmdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseFragment

class FavoriteFragment:BaseFragment() {
    override val viewId: Int
        get() =  R.layout.fragment_favorite

    override fun initFragmentLayout(
        view: View,
        saveInstanceState: Bundle?,
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
    }

    companion object {
        fun newInstance():FavoriteFragment=FavoriteFragment()
    }
}