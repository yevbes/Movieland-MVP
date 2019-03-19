package com.yevbes.movieland.data.network.interceptors

import com.yevbes.movieland.utils.AppConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        if (original.url().encodedPath().equals("/${AppConfig.API_VERSION}/auth/request_token", true)
            || original.url().encodedPath().equals("/${AppConfig.API_VERSION}/auth/access_token", true)
            && original.method().equals("post", true)
        ) {
            return chain.proceed(original)
        }
        val requestBuilder = original.newBuilder()
            .header("Authorization", AppConfig.API_READ_ACCESS_TOKEN)
            .header("Content-Type", AppConfig.CONTENT_TYPE)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}