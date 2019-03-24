package com.yevbes.movieland.domain

import com.yevbes.movieland.data.network.rest.RestService
import com.yevbes.movieland.data.network.rest.ServiceGenerator
import com.yevbes.movieland.data.res.TopRatedMoviesRes
import com.yevbes.movieland.utils.AndroidDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object TopRatedInteractor : TopRatedInteractorContract {

    interface OnTopRatedMoviesObtained {
        fun onSuccess(topRatedMoviesRes: TopRatedMoviesRes)
        fun onFailure(e: Throwable)
        fun onComplete()
    }

    private val restService = ServiceGenerator.createService(RestService::class.java)

    override fun getTopRatedMovies(
        listener: OnTopRatedMoviesObtained,
        compositeDisposable: AndroidDisposable
    ) {
        compositeDisposable.add(
            restService
                .getTopRatedMovies(
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TopRatedMoviesRes>() {

                    override fun onNext(@NonNull topRatedMoviesRes: TopRatedMoviesRes) {
                        listener.onSuccess(topRatedMoviesRes)
                    }

                    override fun onError(@NonNull e: Throwable) {
                        listener.onFailure(e)
                    }

                    override fun onComplete() {
                        listener.onComplete()
                    }
                })
        )
    }
}