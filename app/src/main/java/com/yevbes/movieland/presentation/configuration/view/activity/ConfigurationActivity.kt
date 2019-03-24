package com.yevbes.movieland.presentation.configuration.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog
import com.yevbes.movieland.R
import com.yevbes.movieland.presentation.configuration.ConfigurationContract
import com.yevbes.movieland.presentation.configuration.presenter.ConfigurationPresenter
import com.yevbes.movieland.presentation.main.view.activity.MainActivity
import com.yevbes.movieland.utils.AndroidDisposable

class ConfigurationActivity : AppCompatActivity(), ConfigurationContract.View {

    private lateinit var mPresenter: ConfigurationContract.Presenter
    private lateinit var cl: CoordinatorLayout
    private lateinit var alertDialog: LottieAlertDialog
    private val compositeDisposable = AndroidDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        cl = findViewById(R.id.cl)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupViews()
        mPresenter = ConfigurationPresenter(this)
        mPresenter.getConfiguration(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun loadMainActivity() {
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    override fun showLoadDialog() {
        alertDialog.show()
    }

    override fun dismissDialog() {
        alertDialog.dismiss()
    }

    override fun displayNetworkStatusError(errorMessage: String) {
        Snackbar.make(cl,errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    override fun displayServerError(errorMessage: String) {
        Snackbar.make(cl, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        alertDialog = LottieAlertDialog.Builder(this, DialogTypes.TYPE_LOADING)
            .setTitle(getString(R.string.loading))
            .setDescription(getString(R.string.please_wait_server_configurations))
            .build()
        alertDialog.setCancelable(false)
    }
}
