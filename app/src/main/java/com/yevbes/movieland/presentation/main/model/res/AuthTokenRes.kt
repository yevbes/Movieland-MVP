package com.yevbes.movieland.presentation.main.model.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthTokenRes(
    @SerializedName("status_code")
    @Expose
    var statusCode: Int,

    @SerializedName("status_message")
    @Expose
    var statusMessage: String,

    @SerializedName("success")
    @Expose
    var success: Boolean,

    @SerializedName("request_token")
    @Expose
    var requestToken: String
)