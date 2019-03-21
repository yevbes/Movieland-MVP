package com.yevbes.movieland.presentation.main.presenter

import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.R
import com.yevbes.movieland.data.managers.PreferencesManager
import com.yevbes.movieland.domain.MainInteractor
import com.yevbes.movieland.presentation.main.MainContract
import com.yevbes.movieland.presentation.main.model.res.AuthAccessTokenRes
import com.yevbes.movieland.presentation.main.model.res.AuthTokenRes
import com.yevbes.movieland.presentation.main.model.res.ConfigurationRes
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.NetworkStatusChecker
import okhttp3.Response

class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {

    private val mInteractor = MainInteractor
    private val context = MovielandApplication.getApplication().applicationContext
    private val preferencesManager = PreferencesManager

    override fun getConfiguration(compositeDisposable: AndroidDisposable) {
        mInteractor.getConfiguration(object : MainInteractor.OnConfigurationObtained {
            override fun onSuccess(configurationRes: ConfigurationRes) {
                // TODO: save configuration in some place & get it from first app start 
            }

            override fun onFailure(e: Throwable) {
                e.printStackTrace()
                mView.displayServerError(
                    context.resources.getString(R.string.server_data_error_message)
                )
            }

            override fun onComplete() {

            }

        },compositeDisposable)
    }



    override fun getAuthRequestToken(compositeDisposable: AndroidDisposable) {

        if (NetworkStatusChecker.isInternetConnected(context)) {
            mInteractor.getAuthRequestToken(object : MainInteractor.OnAuthRequestTokenObtained {
                override fun onSuccess(authTokenRes: AuthTokenRes) {
                    if (authTokenRes.success) {
                        preferencesManager.saveAuthRequestToken(authTokenRes.requestToken)
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
                    approveRequestToken(compositeDisposable)
                }

            }, compositeDisposable)

        } else {
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }

    override fun approveRequestToken(compositeDisposable: AndroidDisposable) {
        if (NetworkStatusChecker.isInternetConnected(context)) {
            mInteractor.approveRequestToken(object : MainInteractor.OnRequestTokenApproved {
                override fun onSuccess(response: Any) {
                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                    mView.displayAuthenticationError(
                        context.resources.getString(R.string.error_message_approve_request_token)
                    )
                }

                override fun onComplete() {
                }

            },compositeDisposable)
        }else{
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }

    override fun getAccessToken(compositeDisposable: AndroidDisposable) {
        if (NetworkStatusChecker.isInternetConnected(context)) {
            mInteractor.getAccessToken(object : MainInteractor.OnAccessTokenObtained {
                override fun onSuccess(authAccessTokenRes: AuthAccessTokenRes) {
                    if (authAccessTokenRes.statusCode == 200 && authAccessTokenRes.success) {
                        preferencesManager.saveAccountId(authAccessTokenRes.accountID)
                        preferencesManager.saveAuthAccessToken(authAccessTokenRes.accessToken)
                    }else{
                        mView.displayAuthenticationError(
                            context.resources.getString(R.string.error_message_get_auth_access_token)
                        )
                    }
                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                    mView.displayAuthenticationError(
                        context.resources.getString(R.string.error_message_get_auth_access_token)
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