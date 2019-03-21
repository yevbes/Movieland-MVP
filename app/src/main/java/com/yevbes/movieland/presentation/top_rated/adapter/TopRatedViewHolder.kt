package com.yevbes.movieland.presentation.top_rated.adapter

import android.support.v7.widget.RecyclerView

import com.yevbes.movieland.databinding.TopRatedMoviesRowBinding
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes

class TopRatedViewHolder(private val binding: TopRatedMoviesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TopRatedMoviesRes.Result) {
        binding.tvTitle.text = movie.title
        binding.tvSummary.text = movie.overview
        binding.tvDate.text = movie.releaseDate
        binding.circularRating.progress = (movie.voteAverage * 10).toFloat()
    }
}
