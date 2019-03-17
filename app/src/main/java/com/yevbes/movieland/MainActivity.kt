package com.yevbes.movieland

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yevbes.movieland.interfaces.contracts.MainContract
import com.yevbes.movieland.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun displayAuthenticationError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayNetworkStatusError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
