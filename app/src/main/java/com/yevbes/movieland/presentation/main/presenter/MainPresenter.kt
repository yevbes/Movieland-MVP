package com.yevbes.movieland.presentation.main.presenter

import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.R
import com.yevbes.movieland.data.managers.PreferencesManager
import com.yevbes.movieland.domain.MainInteractor
import com.yevbes.movieland.presentation.main.MainContract
import com.yevbes.movieland.presentation.main.model.res.AuthTokenRes
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.NetworkStatusChecker

class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {
    private val mInteractor = MainInteractor
    private val context = MovielandApplication.getApplication().applicationContext
    private val preferencesManager = PreferencesManager

    override fun getAuthRequestToken(compositeDisposable: AndroidDisposable) {

        if (NetworkStatusChecker.isInternetConnected(context)) {
            mInteractor.getAuthRequestToken(object : MainInteractor.OnAuthRequestTokenObtained {
                override fun onSuccess(authTokenRes: AuthTokenRes) {
                    if (authTokenRes.statusCode == 200) {
                        preferencesManager.saveAuthToken(authTokenRes.requestToken)
                    } else {
                        mView.displayAuthenticationError(
                            context.resources.getString(R.string.error_message_get_auth_token)
                        )
                    }
                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                    mView.displayAuthenticationError(
                        context.resources.getString(R.string.error_message_get_auth_token)
                    )
                }

                override fun onComplete() {

                }

            }, compositeDisposable)

        } else {
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }

}