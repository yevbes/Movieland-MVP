package com.yevbes.movieland.presentation.top_rated.view.fragment


import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yevbes.movieland.presentation.main.view.activity.MainActivity
import com.yevbes.movieland.R
import com.yevbes.movieland.databinding.FragmentTopRatedMoviesBinding
import com.yevbes.movieland.presentation.top_rated.TopRatedMoviesContract
import com.yevbes.movieland.presentation.top_rated.adapter.TopRatedAdapter
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes
import com.yevbes.movieland.presentation.top_rated.presenter.TopRatedMoviesPresenter
import com.yevbes.movieland.utils.AndroidDisposable

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedMoviesFragment : Fragment(), TopRatedMoviesContract.View {
    lateinit var drawerToggle: ActionBarDrawerToggle private set
    lateinit var activity: MainActivity private set
    lateinit var binding: FragmentTopRatedMoviesBinding private set
    lateinit var mPresenter: TopRatedMoviesContract.Presenter private set
    lateinit var compositeDisposable: AndroidDisposable private set

    private val mAdapter: TopRatedAdapter by lazy {
        TopRatedAdapter(arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = TopRatedMoviesPresenter(this)
        compositeDisposable = AndroidDisposable()
    }

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
        activity = getActivity() as MainActivity
        setupRecyclerView()
        mPresenter.getTopRatedMovies(compositeDisposable)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (::drawerToggle.isInitialized) {
            drawerToggle.onConfigurationChanged(newConfig)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    override fun displayNetworkStatusError(errorMessage: String) {
        Snackbar.make(activity.binding.cl,errorMessage,Snackbar.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )

        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = mAdapter
    }

    override fun showMovies(result: List<TopRatedMoviesRes.Result>) {
        mAdapter.addAllItems(result)
    }

}
