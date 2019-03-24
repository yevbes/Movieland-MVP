package com.yevbes.movieland.presentation.configuration

import com.yevbes.movieland.domain.ConfigurationInteractor
import com.yevbes.movieland.utils.AndroidDisposable

interface ConfigurationContract {

    interface View {
        fun displayNetworkStatusError(errorMessage: String)
        fun displayServerError(errorMessage: String)
        fun showLoadDialog()
        fun dismissDialog()
        fun loadMainActivity()
    }

    interface Presenter {
        fun getConfiguration(compositeDisposable: AndroidDisposable)
    }
}