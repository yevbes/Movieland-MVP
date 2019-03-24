package com.yevbes.movieland.presentation.top_rated.adapter

import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.data.res.MoviesRes
import com.yevbes.movieland.databinding.TopRatedMoviesRowBinding

class TopRatedViewHolder(private val binding: TopRatedMoviesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MoviesRes.Result) {
        /*
         binding.tvTitle.text = movie.title
         binding.tvSummary.text = movie.overview
         binding.tvDate.text = movie.releaseDate*/
        binding.movie = movie
        binding.circularRating.progress = (movie.voteAverage * 10).toFloat()

        val context = MovielandApplication.getApplication().applicationContext
        val config = MovielandApplication.getConfigurationServer()
        val imgUrl = config.images.secureBaseUrl + config.images.posterSizes[2] + movie.posterPath

        // TODO: set placeholder
        Glide.with(context)
            .load(imgUrl)
//            .placeholder(lottieAnimationView.drawable)
            .into(binding.poster)


    }
}
