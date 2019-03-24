package com.yevbes.movieland.domain

import com.yevbes.movieland.data.managers.PreferencesManager
import com.yevbes.movieland.data.network.rest.RestService
import com.yevbes.movieland.data.network.rest.ServiceGenerator
import com.yevbes.movieland.presentation.main.MainContract
import com.yevbes.movieland.data.req.AuthAccessTokenReq
import com.yevbes.movieland.data.req.AuthTokenReq
import com.yevbes.movieland.data.res.AuthAccessTokenRes
import com.yevbes.movieland.data.res.AuthTokenRes
import com.yevbes.movieland.data.res.ConfigurationRes
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.AppConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object MainInteractor : MainInteractorContract {
    interface OnAuthRequestTokenObtained {
        fun onSuccess(authTokenRes: AuthTokenRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    interface OnAccessTokenObtained {
        fun onSuccess(authAccessTokenRes: AuthAccessTokenRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    interface OnRequestTokenApproved {
        fun onSuccess(response: Any)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    interface OnConfigurationObtained {
        fun onSuccess(configurationRes: ConfigurationRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    private val restService = ServiceGenerator.createService(RestService::class.java)
    private val preferencesManager = PreferencesManager


    override fun getConfiguration(
        listener: MainInteractor.OnConfigurationObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            restService.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<ConfigurationRes>() {
                        override fun onComplete() {
                            listener.onComplete()
                        }

                        override fun onNext(t: ConfigurationRes) {
                            listener.onSuccess(t)
                        }

                        override fun onError(e: Throwable) {
                            listener.onFailure(e)
                        }
                    }
                )
        )
    }


    override fun getAuthRequestToken(
        listener: OnAuthRequestTokenObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            restService
                .getAuthRequestToken(
                    AuthTokenReq(AppConfig.REDIRECT_TO)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<AuthTokenRes>() {

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
                )
        )
    }

    override fun approveRequestToken(
        listener: OnRequestTokenApproved,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            restService.approveRequestToken(
                preferencesManager.getAuthRequestToken()!!
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<Any>() {
                        override fun onComplete() {
                            listener.onComplete()
                        }

                        override fun onNext(response: Any) {
                            listener.onSuccess(response)
                        }

                        override fun onError(e: Throwable) {
                            listener.onFailure(e)
                        }

                    }
                )
        )
    }


    override fun getAccessToken(
        listener: MainInteractor.OnAccessTokenObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            restService
                .getAuthAccessToken(
                    AuthAccessTokenReq(preferencesManager.getAuthRequestToken()!!)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<AuthAccessTokenRes>() {
                        override fun onComplete() {
                            listener.onComplete()
                        }

                        override fun onNext(@NonNull authAccessTokenRes: AuthAccessTokenRes) {
                            listener.onSuccess(authAccessTokenRes)
                        }

                        override fun onError(@NonNull e: Throwable) {
                            listener.onFailure(e)
                        }

                    }
                )
        )
    }

}
