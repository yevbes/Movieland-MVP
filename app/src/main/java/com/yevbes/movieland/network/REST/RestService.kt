package com.yevbes.movieland.network.REST

import com.yevbes.movieland.network.entities.req.AuthTokenReq
import com.yevbes.movieland.network.entities.res.AuthTokenRes
import com.yevbes.movieland.utils.AppConfig
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestService {

    @Headers(
        "Authorization: Bearer ${AppConfig.API_READ_ACCESS_TOKEN}",
        "Content-Type: ${AppConfig.CONTENT_TYPE}"
    )
    @POST("/auth/request_token")
    fun getAuthRequestToken(
        @Field("api_key") apiKey: String,
        @Field("access_token") accessToken: String,
        @Body redirectTo: AuthTokenReq
    ): Observable<AuthTokenRes>
}
