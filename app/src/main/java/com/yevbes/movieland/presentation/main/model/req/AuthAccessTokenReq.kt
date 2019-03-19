package com.yevbes.movieland.presentation.main.model.req

import com.google.gson.annotations.SerializedName

data class AuthAccessTokenReq(
    @SerializedName("request_token")
    val requestToken: String
)