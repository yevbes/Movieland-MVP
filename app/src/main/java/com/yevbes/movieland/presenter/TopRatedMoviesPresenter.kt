package com.yevbes.movieland.presenter

import com.yevbes.movieland.interfaces.contracts.TopRatedMoviesContract

class TopRatedMoviesPresenter(
    private val mView: TopRatedMoviesContract.View,
    private val mRepository: TopRatedMoviesContract.Repository
) : TopRatedMoviesContract.Presenter {
}