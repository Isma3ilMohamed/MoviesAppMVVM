package com.thedevwolf.mvvmdemo.utils

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity


// to pass context to binding adapter
fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}