package com.yevbes.movieland.network.entities.req

import com.google.gson.annotations.SerializedName

data class AuthAccessTokenReq(
    @SerializedName("request_token")
    val requestToken: String
)