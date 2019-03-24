package com.yevbes.movieland.domain

import com.yevbes.movieland.utils.AndroidDisposable

interface MainInteractorContract {

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
}