package com.yevbes.movieland.presentation.main

import com.yevbes.movieland.domain.MainInteractor
import com.yevbes.movieland.utils.AndroidDisposable

interface MainContract {
    interface View {
        fun displayAuthenticationError(errorMessage: String)
        fun displayNetworkStatusError(errorMessage: String)
        fun displayServerError(errorMessage: String)
        fun loadTopRatedFragment()
        fun showLoadDialog()
        fun dismissDialog()
//        fun fetchData()
//        fun reloadData()
    }

    interface Presenter {
        fun getAuthRequestToken(compositeDisposable: AndroidDisposable)
        fun getAccessToken(compositeDisposable: AndroidDisposable)
        fun approveRequestToken(compositeDisposable: AndroidDisposable)
        fun getConfiguration(compositeDisposable: AndroidDisposable)
    }
}