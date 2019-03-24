package com.yevbes.movieland.domain

import com.yevbes.movieland.utils.AndroidDisposable

interface TopRatedInteractorContract {
    fun getTopRatedMovies(
        listener: TopRatedInteractor.OnTopRatedMoviesObtained,
        compositeDisposable: AndroidDisposable
    )
}