package com.yevbes.movieland.network.REST

import com.yevbes.movieland.network.entities.req.AuthTokenReq
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

  /*  @GET("authentication/token/new")
    fun getAuthRequestToken(
        @Query("api_key") apiKey: String
    ): Observable<Response<AuthTokenRes>>*/
}
