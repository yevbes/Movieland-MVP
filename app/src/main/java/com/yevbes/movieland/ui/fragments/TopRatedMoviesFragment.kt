package com.yevbes.movieland.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yevbes.movieland.MainActivity
import com.yevbes.movieland.R
import com.yevbes.movieland.databinding.FragmentTopRatedMoviesBinding
import com.yevbes.movieland.interfaces.contracts.TopRatedMoviesContract

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedMoviesFragment : Fragment(), TopRatedMoviesContract.View {

    lateinit var drawerToggle: ActionBarDrawerToggle private set
    lateinit var activity: MainActivity private set
    lateinit var binding: FragmentTopRatedMoviesBinding private set


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (::drawerToggle.isInitialized) {
            drawerToggle.onConfigurationChanged(newConfig)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity.binding.nv.menu.findItem(R.id.nav_top_rated_movies).isChecked = false
    }

    private fun setupViews() {
        activity = getActivity() as MainActivity
        activity.setTitle(R.string.action_top_rated_movies)

        activity.binding.abl.visibility = View.GONE
//        activity.binding.nsv.visibility = View.GONE
        activity.binding.nv.menu.findItem(R.id.nav_top_rated_movies).isChecked = true

        activity.setSupportActionBar(binding.tb)
        val actionBar = activity.supportActionBar

        actionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        drawerToggle = ActionBarDrawerToggle(
            activity, activity.binding.dl,
            binding.tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        activity.binding.dl.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

}
