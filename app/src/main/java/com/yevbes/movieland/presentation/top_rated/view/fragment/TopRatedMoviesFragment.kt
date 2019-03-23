package com.yevbes.movieland.presentation.top_rated.view.fragment


import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
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
        setHasOptionsMenu(true)
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
        setupRefreshSwipeView()
        mPresenter.getTopRatedMovies(compositeDisposable)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_top_rated_movies, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_search -> {
                // TODO: Implement search
            }

           /* R.id.action_filter_desc -> {

            }

            R.id.action_filter_asc -> {

            }*/
        }
        return super.onOptionsItemSelected(item)
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

    private fun setupRefreshSwipeView(){
        // TODO decor swipe refresh view
        binding.srl.isRefreshing = true
        binding.srl.setOnRefreshListener {
            mPresenter.getTopRatedMovies(compositeDisposable)
        }
    }

    override fun stopSwipeRefreshView() {
        binding.srl.isRefreshing = false
    }

    override fun showMovies(result: List<TopRatedMoviesRes.Result>) {
        mAdapter.addAllItems(result)
    }

    override fun displayServerError(errorMessage: String) {
        Snackbar.make(activity.binding.clMain, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

}
