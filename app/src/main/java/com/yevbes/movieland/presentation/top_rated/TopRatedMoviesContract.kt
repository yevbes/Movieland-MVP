package com.yevbes.movieland.presentation.top_rated

import com.yevbes.movieland.domain.TopRatedInteractor
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes
import com.yevbes.movieland.utils.AndroidDisposable

interface TopRatedMoviesContract {
    interface View {
        fun displayNetworkStatusError(errorMessage: String)
        fun showMovies(result: List<TopRatedMoviesRes.Result>)
        fun displayServerError(errorMessage: String)
        fun stopSwipeRefreshView()
    }

    interface Presenter {
        fun getTopRatedMovies(compositeDisposable: AndroidDisposable)
    }

    interface Interactor {
        fun getTopRatedMovies(
            listener: TopRatedInteractor.OnTopRatedMoviesObtained,
            compositeDisposable: AndroidDisposable
        )
    }
}