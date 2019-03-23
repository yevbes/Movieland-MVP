package com.yevbes.movieland.presentation.top_rated.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.R

import com.yevbes.movieland.databinding.TopRatedMoviesRowBinding
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes

class TopRatedViewHolder(private val binding: TopRatedMoviesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TopRatedMoviesRes.Result) {
       /*
        binding.tvTitle.text = movie.title
        binding.tvSummary.text = movie.overview
        binding.tvDate.text = movie.releaseDate*/
        binding.movie = movie
        binding.circularRating.progress = (movie.voteAverage * 10).toFloat()

        val context = MovielandApplication.getApplication().applicationContext
        val config = MovielandApplication.getConfigurationServer()
        val imgUrl = config.images.baseUrl + config.images.posterSizes[2] + movie.posterPath
//        val drawable = ContextCompat.getDrawable(context,R.drawable.baseline_refresh_animated)
        Glide.with(context)
            .load(imgUrl)
//            .placeholder(drawable)
            .into(binding.poster)
    }
}
