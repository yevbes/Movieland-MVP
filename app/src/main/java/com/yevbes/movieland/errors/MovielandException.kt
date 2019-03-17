package com.yevbes.movieland.errors

data class MovielandException(
    val code: Int, override val message: String
) : Throwable()


