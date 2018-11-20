package com.thedevwolf.mvvmdemo.utils

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso





@BindingAdapter(value = arrayOf("imageUrl", "placeholder"), requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable?) {
  Picasso.get().load(imageUrl).placeholder(placeholder!!).error(placeholder).into(imageView)

}

@BindingAdapter("injectAdapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity!=null&&visibility!=null){
        visibility.observe(parentActivity, Observer{
            value -> view.visibility=value?:View.VISIBLE
        })
    }
}
@BindingAdapter("onNavigationItemSelected")
fun setNavigationBottomListener(view:BottomNavigationView,listener: BottomNavigationView.OnNavigationItemSelectedListener){
    view.setOnNavigationItemSelectedListener(listener)
}
