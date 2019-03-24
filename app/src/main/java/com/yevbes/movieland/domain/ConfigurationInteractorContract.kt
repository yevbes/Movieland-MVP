package com.yevbes.movieland.domain

import com.yevbes.movieland.utils.AndroidDisposable

interface ConfigurationInteractorContract {

    fun getConfiguration(
        listener: ConfigurationInteractor.OnConfigurationObtained,
        compositeDisposable: AndroidDisposable
    )
}