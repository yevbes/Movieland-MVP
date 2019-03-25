package com.yevbes.movieland.presentation.top_rated

import com.yevbes.movieland.data.res.MoviesRes
import com.yevbes.movieland.utils.AndroidDisposable

interface TopRatedMoviesContract {
    interface View {
        fun displayNetworkStatusError(errorMessage: String)
        fun showMovies(result: ArrayList<MoviesRes.Result>)
        fun displayServerError(errorMessage: String)
        fun stopSwipeRefreshView()
        fun startSwipeRefreshView()
    }

    interface Presenter {
        fun getTopRatedMovies(compositeDisposable: AndroidDisposable)
    }
}