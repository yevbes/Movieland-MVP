package com.yevbes.movieland.network.managers

import android.content.SharedPreferences
import com.yevbes.movieland.utils.ConstantManager
import com.yevbes.movieland.utils.MovielandApplication

object PreferencesManager {

    private var mSharedPreferences: SharedPreferences = MovielandApplication.getSharedPreferences()

    fun saveAuthToken(authToken: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.AUTH_TOKEN_KEY, authToken)
        editor.apply()
    }

    fun getAuthToken(): String? {
        return mSharedPreferences.getString(ConstantManager.AUTH_TOKEN_KEY, "null")
    }
}