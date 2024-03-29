package com.yevbes.movieland.utils

interface AppConfig {
    companion object {
        const val API_VERSION = 4
        const val BASE_URL = "https://api.example.org/"
        const val API_KEY = "API_KEY"
        const val SITE_URL = "SITE_URL"
        const val API_READ_ACCESS_TOKEN = "API_READ_ACCESS_TOKEN"
        const val REDIRECT_TO = "http://www.example.org/"
        const val CONTENT_TYPE = "application/json;charset=utf-8"
    }
}
