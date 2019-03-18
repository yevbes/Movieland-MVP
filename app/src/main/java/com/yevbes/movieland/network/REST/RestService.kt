package com.yevbes.movieland.network.REST

import com.yevbes.movieland.network.entities.req.AuthAccessTokenReq
import com.yevbes.movieland.network.entities.req.AuthTokenReq
import com.yevbes.movieland.network.entities.res.AuthAccessTokenRes
import com.yevbes.movieland.network.entities.res.AuthTokenRes
import com.yevbes.movieland.utils.AppConfig
import io.reactivex.Observable
import retrofit2.http.*

interface RestService {

    @POST("auth/request_token")
    @Headers(
        "Authorization: ${AppConfig.API_READ_ACCESS_TOKEN}",
        "Content-Type: ${AppConfig.CONTENT_TYPE}"
    )
    fun getAuthRequestToken(
        @Body redirectTo: AuthTokenReq
    ): Observable<AuthTokenRes>

    @POST("auth/access_token")
    @Headers(
        "Authorization: ${AppConfig.API_READ_ACCESS_TOKEN}",
        "Content-Type: ${AppConfig.CONTENT_TYPE}"
    )
    fun getAuthAccessToken(
        @Body requestToken: AuthAccessTokenReq
    ): Observable<AuthAccessTokenRes>
}
