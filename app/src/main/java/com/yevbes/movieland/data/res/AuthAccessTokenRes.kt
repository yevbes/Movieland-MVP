package com.yevbes.movieland.data.res

import com.google.gson.annotations.SerializedName

data class AuthAccessTokenRes(
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("account_id")
    val accountID: String
)