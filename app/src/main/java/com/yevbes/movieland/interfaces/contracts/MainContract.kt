package com.yevbes.movieland.interfaces.contracts

interface MainContract {
    // Represent elements into the Views
    interface View {
        fun displayAuthenticationError(errorMessage: String)
        fun displayNetworkStatusError(errorMessage: String)
    }

    // Life cycles and events
    interface Presenter {
        fun getAuthRequestToken()
        fun onDestroy()
    }

    // Load/Store the data into BD
    interface Repository {

    }
}