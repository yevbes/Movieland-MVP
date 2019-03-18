package com.yevbes.movieland.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import io.reactivex.plugins.RxJavaPlugins



/** class for initialize DataBase, get SharedPrefs, ... */
class MovielandApplication : Application() {

    companion object {
        private lateinit var mSharedPreferences: SharedPreferences
        private lateinit var sApplication: Application

        @JvmStatic
        fun getSharedPreferences() : SharedPreferences {
            return mSharedPreferences
        }

        @JvmStatic
        fun getApplication(): Application {
            return sApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        MovielandApplication.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        MovielandApplication.sApplication = this
        RxJavaPlugins.setErrorHandler { }
    }
}