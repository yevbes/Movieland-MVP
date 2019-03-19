package com.yevbes.movieland.data.managers

import android.content.SharedPreferences
import com.yevbes.movieland.utils.ConstantManager
import com.yevbes.movieland.MovielandApplication

object PreferencesManager {

    private var mSharedPreferences: SharedPreferences = MovielandApplication.getSharedPreferences()

    fun saveAuthRequestToken(authToken: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.AUTH_REQUEST_TOKEN_KEY, authToken)
        editor.apply()
    }

    fun getAuthRequestToken(): String? {
        return mSharedPreferences.getString(ConstantManager.AUTH_REQUEST_TOKEN_KEY, "null")
    }

    fun saveAuthAccessToken(authAccessToken: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.AUTH_ACCESS_TOKEN_KEY, authAccessToken)
        editor.apply()
    }

    fun getAuthAccessToken(): String? {
        return mSharedPreferences.getString(ConstantManager.AUTH_ACCESS_TOKEN_KEY, "null")
    }

    fun saveAccountId(accountId : String) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.ACCOUNT_ID, accountId)
        editor.apply()
    }

    fun getAccountId(): String? {
        return mSharedPreferences.getString(ConstantManager.ACCOUNT_ID,"null")
    }
}