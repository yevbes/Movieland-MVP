package com.yevbes.movieland.data.network.rest

import com.yevbes.movieland.data.req.AuthAccessTokenReq
import com.yevbes.movieland.data.req.AuthTokenReq
import com.yevbes.movieland.data.res.AuthAccessTokenRes
import com.yevbes.movieland.data.res.AuthTokenRes
import com.yevbes.movieland.data.res.ConfigurationRes
import com.yevbes.movieland.data.res.TopRatedMoviesRes
import com.yevbes.movieland.utils.AppConfig
import io.reactivex.Observable
import retrofit2.Response
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

    @POST("${AppConfig.SITE_URL}auth/access?request_token={request_token}")
    fun approveRequestToken(
        @Path("request_token") requestToken: String
    ): Observable<Response<Any>>

    @POST("auth/access_token")
    @Headers(
        "Authorization: ${AppConfig.API_READ_ACCESS_TOKEN}",
        "Content-Type: ${AppConfig.CONTENT_TYPE}"
    )
    fun getAuthAccessToken(
        @Body requestToken: AuthAccessTokenReq
    ): Observable<AuthAccessTokenRes>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
    ): Observable<TopRatedMoviesRes>

    @GET("configuration")
    fun getConfiguration(
    ): Observable<ConfigurationRes>
}
