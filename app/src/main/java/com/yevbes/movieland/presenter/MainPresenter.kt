package com.yevbes.movieland.presenter

import com.yevbes.movieland.R
import com.yevbes.movieland.interfaces.contracts.MainContract
import com.yevbes.movieland.repository.MainRepository
import com.yevbes.movieland.network.REST.RestService
import com.yevbes.movieland.network.REST.ServiceGenerator
import com.yevbes.movieland.network.entities.req.AuthTokenReq
import com.yevbes.movieland.network.entities.res.AuthTokenRes
import com.yevbes.movieland.network.managers.PreferencesManager
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.AppConfig
import com.yevbes.movieland.utils.MovielandApplication
import com.yevbes.movieland.utils.NetworkStatusChecker
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class MainPresenter(
    private val mView: MainContract.View
) : MainContract.Presenter {
    private val mRepository: MainContract.Repository = MainRepository
    private val restService = ServiceGenerator.createService(RestService::class.java)
    private val preferencesManager = PreferencesManager
    private val context = MovielandApplication.getApplication().applicationContext
    private val compositeDisposable = AndroidDisposable()

    override fun getAuthRequestToken() {
        if (NetworkStatusChecker.isInternetConnected(context)) {
            compositeDisposable.add(
                getObservable()
                    .subscribeWith(getObserver())
            )
        } else {
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }

    private fun getObservable(): Observable<AuthTokenRes> {
        return restService
            .getAuthRequestToken(
                AppConfig.API_KEY,
                AppConfig.API_READ_ACCESS_TOKEN,
                AuthTokenReq(AppConfig.REDIRECT_TO)
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserver(): DisposableObserver<AuthTokenRes> {
        return object : DisposableObserver<AuthTokenRes>() {

            override fun onNext(@NonNull authTokenRes: AuthTokenRes) {
                if (authTokenRes.statusCode == 200) {
                    preferencesManager.saveAuthToken(authTokenRes.requestToken)
                } else {
                    mView.displayAuthenticationError(
                        context.resources.getString(R.string.error_message_get_auth_token)
                    )
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                mView.displayAuthenticationError(
                    context.resources.getString(R.string.error_message_get_auth_token)
                )
            }

            override fun onComplete() {

            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}