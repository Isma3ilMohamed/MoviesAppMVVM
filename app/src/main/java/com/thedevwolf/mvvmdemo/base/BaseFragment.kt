package com.thedevwolf.mvvmdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {
    protected abstract val viewId: Int
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(viewId, container, false)

        view.apply {
            initFragmentLayout(view, savedInstanceState, inflater, container)
        }
        return view
    }

    protected abstract fun initFragmentLayout(view: View, saveInstanceState: Bundle?, inflater: LayoutInflater, container: ViewGroup?)

}