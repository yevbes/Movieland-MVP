package com.yevbes.movieland.presentation.main

import com.yevbes.movieland.domain.MainInteractor
import com.yevbes.movieland.utils.AndroidDisposable

interface MainContract {
    interface View {
        fun displayAuthenticationError(errorMessage: String)
        fun displayNetworkStatusError(errorMessage: String)
        fun displayServerError(errorMessage: String)
//        fun fetchData()
//        fun reloadData()
    }

    interface Presenter {
        fun getAuthRequestToken(compositeDisposable: AndroidDisposable)
        fun getAccessToken(compositeDisposable: AndroidDisposable)
        fun approveRequestToken(compositeDisposable: AndroidDisposable)
        fun getConfiguration(compositeDisposable: AndroidDisposable)
    }

    interface Interactor {
        fun getAuthRequestToken(
            listener: MainInteractor.OnAuthRequestTokenObtained,
            compositeDisposable: AndroidDisposable
        )

        fun getAccessToken(
            listener: MainInteractor.OnAccessTokenObtained,
            compositeDisposable: AndroidDisposable
        )

        fun approveRequestToken(
            listener: MainInteractor.OnRequestTokenApproved,
            compositeDisposable: AndroidDisposable
        )

        fun getConfiguration(
            listener: MainInteractor.OnConfigurationObtained,
            compositeDisposable: AndroidDisposable
        )
    }
}