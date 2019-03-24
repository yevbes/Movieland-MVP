package com.yevbes.movieland.presentation.top_rated.presenter

import com.yevbes.movieland.MovielandApplication
import com.yevbes.movieland.R
import com.yevbes.movieland.domain.TopRatedInteractor
import com.yevbes.movieland.presentation.top_rated.TopRatedMoviesContract
import com.yevbes.movieland.data.res.MoviesRes
import com.yevbes.movieland.utils.AndroidDisposable
import com.yevbes.movieland.utils.NetworkStatusChecker

class TopRatedMoviesPresenter(
    private val mView: TopRatedMoviesContract.View
) : TopRatedMoviesContract.Presenter {
    private val mInteractor = TopRatedInteractor
    private val context = MovielandApplication.getApplication().applicationContext

    override fun getTopRatedMovies(compositeDisposable: AndroidDisposable) {
        if (NetworkStatusChecker.isInternetConnected(context)) {
            mInteractor.getTopRatedMovies(object : TopRatedInteractor.OnTopRatedMoviesObtained {
                override fun onSuccess(moviesRes: MoviesRes) {
                    val list = ArrayList<MoviesRes.Result>()
                    list.addAll(moviesRes.results)
                    mView.showMovies(list)
                }

                override fun onFailure(e: Throwable) {
                    e.printStackTrace()
                    mView.displayServerError(
                        context.resources.getString(R.string.server_data_error_message)
                    )
                    mView.stopSwipeRefreshView()
                }

                override fun onComplete() {
                    mView.stopSwipeRefreshView()
                }

            }, compositeDisposable)

        } else {
            mView.displayNetworkStatusError(context.resources.getString(R.string.error_message_network_connection))
        }
    }
}