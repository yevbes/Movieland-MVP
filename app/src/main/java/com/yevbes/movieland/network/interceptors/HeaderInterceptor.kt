package com.yevbes.movieland.network.interceptors

import com.yevbes.movieland.network.managers.PreferencesManager
import com.yevbes.movieland.utils.AppConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .header("Authorization", PreferencesManager.getAuthToken()!!)
            .header("Content-Type", AppConfig.CONTENT_TYPE)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}