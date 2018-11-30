package com.thedevwolf.mvvmdemo.ui.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.data.model.Movie
import com.thedevwolf.mvvmdemo.databinding.RecycleMovieBinding
import com.thedevwolf.mvvmdemo.ui.activity.DetailActivity
import com.thedevwolf.mvvmdemo.utils.Constants
import com.thedevwolf.mvvmdemo.vm.adapter.MovieViewModel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class FavoriteAdapter : ListAdapter<Movie.Result, FavoriteAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val movieBinding:RecycleMovieBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycle_movie, parent, false
        )
        return ItemViewholder(movieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(private val movieBinding: RecycleMovieBinding) : RecyclerView.ViewHolder(movieBinding.root) {
        private val heroViewModel= MovieViewModel()
        fun bind(item: Movie.Result) = with(itemView) {
            // TODO: Bind the data with View
            heroViewModel.bind(item)
            movieBinding.movie=heroViewModel


            setOnClickListener {
                movieBinding.root.context
                    .apply {
                        startActivity(intentFor<DetailActivity>()
                            .putExtras(Bundle().apply {
                                putParcelable(Constants.MOVIE,item)
                            }))
                    }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie.Result>() {
        override fun areItemsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
            return oldItem == newItem
        }
    }
}

