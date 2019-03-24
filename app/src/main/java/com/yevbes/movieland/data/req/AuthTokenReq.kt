package com.yevbes.movieland.data.req

import com.google.gson.annotations.SerializedName

data class AuthTokenReq(
  @SerializedName("redirect_to")
  private val redirectTo: String
)