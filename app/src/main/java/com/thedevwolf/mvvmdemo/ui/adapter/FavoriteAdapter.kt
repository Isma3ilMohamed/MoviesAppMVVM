package com.thedevwolf.mvvmdemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.data.db.Entity.MovieEntity
import com.thedevwolf.mvvmdemo.databinding.RecycleMovieBinding
import com.thedevwolf.mvvmdemo.vm.adapter.MovieViewModel

class FavoriteAdapter() : ListAdapter<Movie.Result, FavoriteAdapter.FavoriteViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val movieBinding: RecycleMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycle_movie, parent, false
        )
        return FavoriteViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class FavoriteViewHolder(private val movieBinding: RecycleMovieBinding) :
        RecyclerView.ViewHolder(movieBinding.root) {
        private val heroViewModel= MovieViewModel()

            fun bind(item: Movie.Result?) {
                heroViewModel.bind(item!!)
                movieBinding.movie=heroViewModel
            }

    }


    companion object {
        val DIFF_UTIL: DiffUtil.ItemCallback<Movie.Result> = object : DiffUtil.ItemCallback<Movie.Result>() {
            override fun areItemsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
                return oldItem == newItem
            }

        }
    }
}