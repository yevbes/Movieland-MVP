package com.yevbes.movieland

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.yevbes.movieland.data.res.ConfigurationRes
import io.reactivex.plugins.RxJavaPlugins



/** class for initialize DataBase, get SharedPrefs, ... */
class MovielandApplication : Application() {

    companion object {
        private lateinit var configurationServer: ConfigurationRes
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

        @JvmStatic
        fun setConfigurationServer(configurationRes: ConfigurationRes) {
            this.configurationServer = configurationRes
        }

        @JvmStatic
        fun getConfigurationServer() : ConfigurationRes {
            return configurationServer
        }
    }

    override fun onCreate() {
        super.onCreate()
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sApplication = this
        RxJavaPlugins.setErrorHandler { }
    }
}