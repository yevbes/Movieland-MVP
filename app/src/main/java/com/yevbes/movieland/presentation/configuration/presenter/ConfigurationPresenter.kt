package com.yevbes.movieland.presentation.configuration.presenter

import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.R
import com.yevbes.movieland.data.res.ConfigurationRes
import com.yevbes.movieland.domain.ConfigurationInteractor
import com.yevbes.movieland.presentation.configuration.ConfigurationContract
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.NetworkStatusChecker

class ConfigurationPresenter(private val mView: ConfigurationContract.View) : ConfigurationContract.Presenter {

    private val mInteractor = ConfigurationInteractor
    private val context = MovielandApplication.getApplication().applicationContext

    override fun getConfiguration(compositeDisposable: AndroidDisposable) {
        if (NetworkStatusChecker.isInternetConnected(context)) {
            mView.showLoadDialog()
            mInteractor.getConfiguration(object : ConfigurationInteractor.OnConfigurationObtained {
                override fun onSuccess(configurationRes: ConfigurationRes) {
                    MovielandApplication.setConfigurationServer(configurationRes)
                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                    mView.dismissDialog()
                    mView.displayServerError(
                        context.resources.getString(R.string.server_data_error_message)
                    )
                }

                override fun onComplete() {
                    mView.loadMainActivity()
                    mView.dismissDialog()
                }

            }, compositeDisposable)
        } else {
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }

}