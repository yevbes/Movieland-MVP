package com.yevbes.movieland.error

data class MovielandException(
    val code: Int, override val message: String
) : Throwable()


