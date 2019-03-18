package com.yevbes.movieland.utils

interface ConstantManager {
    companion object {
        // SharedPrefs
        const val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"
        const val AUTH_ACCESS_TOKEN_KEY = "AUTH_ACCESS_TOKEN_KEY"

        // NavigationDrawer actions
        const val ACTION_TOP_RATED_MOVIES = 1
        const val ACTION_COMING_SOON = 2
        const val ACTION_IN_THEATERS = 3
        const val ACTION_LATEST_TRAILERS = 4
        const val ACTION_MOST_POPULAR = 5
        const val ACTION_SHOWTIMES_TICKETS = 6

    }
}