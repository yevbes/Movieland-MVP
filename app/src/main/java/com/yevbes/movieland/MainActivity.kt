package com.yevbes.movieland

import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.yevbes.movieland.databinding.ActivityMainBinding
import com.yevbes.movieland.interfaces.contracts.MainContract
import com.yevbes.movieland.presenter.MainPresenter
import com.yevbes.movieland.ui.fragments.*
import com.yevbes.movieland.utils.ConstantManager

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainContract.View {
    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private var doubleBackToExitPressedOnce = false
    private var currentDrawerItemID: Int = 0
    lateinit var binding: ActivityMainBinding

    private val topRatedMoviesFragment: TopRatedMoviesFragment by lazy {
        TopRatedMoviesFragment()
    }

    private val comingSoonFragment: ComingSoonFragment by lazy {
        ComingSoonFragment()
    }

    private val inTheatersFragment: InTheatersFragment by lazy {
        InTheatersFragment()
    }

    private val latestTrailersFragment: LatestTrailersFragment by lazy {
        LatestTrailersFragment()
    }

    private val mostPopularMoviesFragment: MostPopularMoviesFragment by lazy {
        MostPopularMoviesFragment()
    }

    private val showtimesTicketsFragment: ShowtimesTicketsFragment by lazy {
        ShowtimesTicketsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        setupViews()

        if (savedInstanceState == null) {
            loadFragment(ConstantManager.ACTION_TOP_RATED_MOVIES)
        }

        // TODO: NavDrawer settings arrow button

        mPresenter = MainPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    private inline fun <T : View> T.postEx(crossinline callback: T.() -> Unit) = post { callback() }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        binding.dl.postEx { closeDrawer(GravityCompat.START) }
        when (p0.itemId) {
            R.id.nav_top_rated_movies -> {
                if (currentDrawerItemID != ConstantManager.ACTION_TOP_RATED_MOVIES) {
                    loadFragment(ConstantManager.ACTION_TOP_RATED_MOVIES)
                }
                return true
            }

            R.id.nav_coming_soon -> {
                if (currentDrawerItemID != ConstantManager.ACTION_COMING_SOON) {
                    loadFragment(ConstantManager.ACTION_COMING_SOON)
                }
                return true

            }

            R.id.nav_in_theaters -> {
                if (currentDrawerItemID != ConstantManager.ACTION_IN_THEATERS) {
                    loadFragment(ConstantManager.ACTION_IN_THEATERS)
                }
                return true

            }

            R.id.nav_latest_trailers -> {
                if (currentDrawerItemID != ConstantManager.ACTION_LATEST_TRAILERS) {
                    loadFragment(ConstantManager.ACTION_LATEST_TRAILERS)
                }
                return true

            }

            R.id.nav_most_popular_movies -> {
                if (currentDrawerItemID != ConstantManager.ACTION_MOST_POPULAR) {
                    loadFragment(ConstantManager.ACTION_MOST_POPULAR)
                }
                return true

            }

            R.id.nav_showtimes_tickets -> {
                if (currentDrawerItemID != ConstantManager.ACTION_SHOWTIMES_TICKETS) {
                    loadFragment(ConstantManager.ACTION_SHOWTIMES_TICKETS)
                }
                return true

            }
            else -> {
                return true

            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        if (binding.dl.isDrawerOpen(GravityCompat.START)){
            binding.dl.closeDrawer(GravityCompat.START)
        }else{
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.double_back_to_exit), Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    private fun setupViews() {
        setSupportActionBar(binding.tb)

        setTitle(R.string.app_name)

        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.tb.setNavigationOnClickListener {
            binding.dl.openDrawer(GravityCompat.START)
        }

        drawerToggle = ActionBarDrawerToggle(
            this, binding.dl, binding.tb,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        binding.nv.setNavigationItemSelectedListener(this)

        binding.dl.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun loadFragment(currentDrawerItemID: Int) {
        clearBackStack()
        this.currentDrawerItemID = currentDrawerItemID
        when (currentDrawerItemID) {
            ConstantManager.ACTION_TOP_RATED_MOVIES -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.clMain,
                        topRatedMoviesFragment,
                        getString(R.string.action_top_rated_movies)
                    )
                    .commit()

            }
            ConstantManager.ACTION_COMING_SOON -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.clMain, comingSoonFragment, getString(R.string.action_coming_soon))
                    .commit()

            }
            ConstantManager.ACTION_IN_THEATERS -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.clMain, inTheatersFragment, getString(R.string.action_in_theaters))
                    .commit()

            }
            ConstantManager.ACTION_LATEST_TRAILERS -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.clMain, latestTrailersFragment, getString(R.string.action_latest_trailers))
                    .commit()

            }
            ConstantManager.ACTION_MOST_POPULAR -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.clMain,
                        mostPopularMoviesFragment,
                        getString(R.string.action_most_popular_movies)
                    )
                    .commit()

            }
            ConstantManager.ACTION_SHOWTIMES_TICKETS -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.clMain,
                        showtimesTicketsFragment,
                        getString(R.string.action_showtimes_tickets)
                    )
                    .commit()

            }
        }
    }

    private fun clearBackStack() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStackImmediate()
        }
    }

    override fun displayAuthenticationError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayNetworkStatusError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
