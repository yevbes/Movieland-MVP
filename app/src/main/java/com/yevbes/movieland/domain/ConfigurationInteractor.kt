package com.yevbes.movieland.domain

import com.yevbes.movieland.data.network.rest.RestService
import com.yevbes.movieland.data.network.rest.ServiceGenerator
import com.yevbes.movieland.data.res.ConfigurationRes
import com.yevbes.movieland.utils.AndroidDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object ConfigurationInteractor : ConfigurationInteractorContract {

    interface OnConfigurationObtained {
        fun onSuccess(configurationRes: ConfigurationRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    private val restService = ServiceGenerator.createService(RestService::class.java)

    override fun getConfiguration(
        listener: ConfigurationInteractor.OnConfigurationObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            ConfigurationInteractor.restService.getConfiguration()
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
}