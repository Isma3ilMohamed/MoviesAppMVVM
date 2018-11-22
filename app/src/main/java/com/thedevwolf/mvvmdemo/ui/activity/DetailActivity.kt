package com.thedevwolf.mvvmdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.base.BaseActivity
import com.thedevwolf.mvvmdemo.databinding.ActivityDetailBinding
import com.thedevwolf.mvvmdemo.utils.Constants.MOVIE
import com.thedevwolf.mvvmdemo.vm.activity.DetailViewModel
import com.thedevwolf.mvvmdemo.vm.factory.DetailFactory

class DetailActivity : BaseActivity() {

    lateinit var detailBinding: ActivityDetailBinding
    lateinit var detailViewModel: DetailViewModel
    lateinit var factory: DetailFactory

    //Rec Var
    var result: Movie.Result? = null

    override fun init(savedInstanceState: Bundle?) {

        detailBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        if (intent.extras != null) result = intent.extras.getParcelable(MOVIE)

        factory = DetailFactory(this.application, result!!)
        detailViewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        detailBinding.movie = detailViewModel


    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_detail
    }

    override fun fullScreen(): Boolean {
        return false
    }

    override fun hideInputType(): Boolean {
        return false
    }


}
