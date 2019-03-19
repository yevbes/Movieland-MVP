package com.yevbes.movieland.presentation.main

import com.yevbes.movieland.domain.MainInteractor
import com.yevbes.movieland.utils.AndroidDisposable

interface MainContract {
    interface View {
        fun displayAuthenticationError(errorMessage: String)
        fun displayNetworkStatusError(errorMessage: String)
//        fun fetchData()
//        fun reloadData()
    }

    interface Presenter {
        fun getAuthRequestToken(compositeDisposable: AndroidDisposable)
    }

    interface Interactor {
        fun getAuthRequestToken(
            listener: MainInteractor.OnAuthRequestTokenObtained,
            compositeDisposable: AndroidDisposable
        )
    }
}