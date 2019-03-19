package com.yevbes.movieland.domain

import com.yevbes.movieland.presentation.main.MainContract
import com.yevbes.movieland.data.network.rest.RestService
import com.yevbes.movieland.data.network.rest.ServiceGenerator
import com.yevbes.movieland.presentation.main.model.req.AuthTokenReq
import com.yevbes.movieland.presentation.main.model.res.AuthTokenRes
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.AppConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object MainInteractor : MainContract.Interactor {

    interface OnAuthRequestTokenObtained {
        fun onSuccess(authTokenRes: AuthTokenRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    private val restService = ServiceGenerator.createService(RestService::class.java)

    override fun getAuthRequestToken(
        listener: OnAuthRequestTokenObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            getObservable()
                .subscribeWith(getObserver(listener))
        )
    }

    private fun getObservable(): Observable<AuthTokenRes> {
        return restService
            .getAuthRequestToken(
                AuthTokenReq(AppConfig.REDIRECT_TO)
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserver(listener: OnAuthRequestTokenObtained): DisposableObserver<AuthTokenRes> {
        return object : DisposableObserver<AuthTokenRes>() {

            override fun onNext(@NonNull authTokenRes: AuthTokenRes) {
                listener.onSuccess(authTokenRes)
            }

            override fun onError(@NonNull e: Throwable) {
                listener.onFailure(e)
            }

            override fun onComplete() {
                listener.onComplete()
            }
        }
    }

}
