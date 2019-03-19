package com.yevbes.movieland.data.network.rest

import com.yevbes.movieland.presentation.main.model.req.AuthAccessTokenReq
import com.yevbes.movieland.presentation.main.model.req.AuthTokenReq
import com.yevbes.movieland.presentation.main.model.res.AuthAccessTokenRes
import com.yevbes.movieland.presentation.main.model.res.AuthTokenRes
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes
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

    @GET("list/{list_id}")
    fun getTopRatedMovies(
        @Path("list_id") listId: Int,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String
    ): Observable<TopRatedMoviesRes>


}
