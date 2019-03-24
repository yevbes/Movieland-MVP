package com.yevbes.movieland.data.req

import com.google.gson.annotations.SerializedName

data class AuthAccessTokenReq(
    @SerializedName("request_token")
    val requestToken: String
)