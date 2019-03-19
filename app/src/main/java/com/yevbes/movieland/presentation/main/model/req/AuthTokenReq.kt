package com.yevbes.movieland.presentation.main.model.req

import com.google.gson.annotations.SerializedName

data class AuthTokenReq(
  @SerializedName("redirect_to")
  private val redirectTo: String
)