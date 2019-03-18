package com.yevbes.movieland.network.managers

import android.content.SharedPreferences
import com.yevbes.movieland.utils.ConstantManager
import com.yevbes.movieland.utils.MovielandApplication

object PreferencesManager {

    private var mSharedPreferences: SharedPreferences = MovielandApplication.getSharedPreferences()

    fun saveAuthToken(authToken: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.AUTH_TOKEN_KEY, "Bearer $authToken")
        editor.apply()
    }

    fun getAuthToken(): String? {
        return mSharedPreferences.getString(ConstantManager.AUTH_TOKEN_KEY, "null")
    }

    fun saveAuthAccessToken(authAccessToken: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.AUTH_ACCESS_TOKEN_KEY, authAccessToken)
        editor.apply()
    }

    fun getAuthAccessToken(): String? {
        return mSharedPreferences.getString(ConstantManager.AUTH_ACCESS_TOKEN_KEY, "null")
    }
}