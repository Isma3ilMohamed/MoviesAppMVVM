package com.thedevwolf.mvvmdemo.base

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.thedevwolf.mvvmdemo.controller.App
import com.thedevwolf.mvvmdemo.data.Repository
import com.thedevwolf.mvvmdemo.di.component.ApiComponent
import com.thedevwolf.mvvmdemo.di.module.ContextModule
import javax.inject.Inject


abstract class BaseActivity:AppCompatActivity() {

     lateinit var injector:ApiComponent



    override fun onCreate(savedInstanceState: Bundle?) {


        //Provide context to dagger component
        injector = (application as App).netComponent
            ?.contextModule(ContextModule(this))!!.build()

        //inject dagger
        injector.inject(this)


        super.onCreate(savedInstanceState)
        if (fullScreen()){
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        //setContentView(getLayoutRes())
        if (hideInputType()){
            hideInput()
        }



        init(savedInstanceState)
    }



    protected abstract fun init(savedInstanceState: Bundle?)
    protected abstract fun getLayoutRes():Int
    protected abstract fun fullScreen():Boolean
    protected abstract fun hideInputType():Boolean
    private fun hideInput(){
        if (currentFocus!=null){
            val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken,0)
        }
    }


}