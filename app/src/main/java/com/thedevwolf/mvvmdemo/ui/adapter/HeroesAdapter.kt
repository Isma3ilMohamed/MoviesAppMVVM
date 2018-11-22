package com.thedevwolf.mvvmdemo.ui.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import com.thedevwolf.moviesappmvvm.data.model.Movie
import com.thedevwolf.mvvmdemo.R
import com.thedevwolf.mvvmdemo.databinding.RecycleMovieBinding
import com.thedevwolf.mvvmdemo.vm.adapter.MovieViewModel



class HeroesAdapter (val onClickListener: (Movie.Result) -> Unit):
    RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

     var heroList:MutableList<Movie.Result?> = mutableListOf()

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): HeroViewHolder {
        val movieBinding:RecycleMovieBinding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycle_movie, parent, false
        )
        return HeroViewHolder(movieBinding)
    }

    override fun onBindViewHolder(@NonNull holder: HeroViewHolder, position: Int) {
        holder.bind( heroList[position]!!)

        holder.itemView.setOnClickListener {
            onClickListener(heroList[position]!!)
        }

    }
    fun addHeros(heros:List<Movie.Result?>?){
        heroList.addAll(heros!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    inner class HeroViewHolder(private val movieBinding: RecycleMovieBinding)
        : RecyclerView.ViewHolder(movieBinding.root) {
        private val heroViewModel= MovieViewModel()
        fun bind( hero: Movie.Result) {
            heroViewModel.bind(hero)
            movieBinding.movie=heroViewModel

        }






    }
}