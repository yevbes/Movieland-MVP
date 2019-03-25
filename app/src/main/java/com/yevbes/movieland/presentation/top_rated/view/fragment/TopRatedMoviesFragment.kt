package com.yevbes.movieland.presentation.top_rated.view.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.yevbes.movieland.R
import com.yevbes.movieland.data.res.MoviesRes
import com.yevbes.movieland.databinding.FragmentTopRatedMoviesBinding
import com.yevbes.movieland.presentation.main.view.activity.MainActivity
import com.yevbes.movieland.presentation.top_rated.TopRatedMoviesContract
import com.yevbes.movieland.presentation.top_rated.adapter.TopRatedAdapter
import com.yevbes.movieland.presentation.top_rated.presenter.TopRatedMoviesPresenter
import com.yevbes.movieland.utils.AndroidDisposable

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedMoviesFragment : Fragment(), SearchView.OnQueryTextListener, TopRatedMoviesContract.View {
    lateinit var activity: MainActivity private set
    lateinit var binding: FragmentTopRatedMoviesBinding private set
    lateinit var mPresenter: TopRatedMoviesContract.Presenter private set
    lateinit var compositeDisposable: AndroidDisposable private set
    //    lateinit var mSearchString: String private set
    lateinit var searchView: SearchView private set

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

        /*  if (savedInstanceState != null) {
              mSearchString = savedInstanceState.getString(ConstantManager.KEY_QUERY_TEXT_CHANGE)!!
          }
  */
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*  mSearchString = searchView.query.toString()
          outState.putString(ConstantManager.KEY_QUERY_TEXT_CHANGE, mSearchString)*/
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_top_rated_movies, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
        searchView.setOnQueryTextListener(this)

        /*  //focus the SearchView
          if (::mSearchString.isInitialized && !mSearchString.isEmpty()) {
              searchItem.expandActionView()
              searchView.setQuery(mSearchString, true)
              searchView.clearFocus()
          }*/

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_filter -> {
                item.isChecked = !item.isChecked
                binding.filterLayout.visibility =
                    if (binding.filterLayout.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        mAdapter.filter.filter(p0)
        return true
    }

    override fun displayNetworkStatusError(errorMessage: String) {
        Snackbar.make(activity.binding.cl, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        binding.rv.layoutManager = GridLayoutManager(
            activity, resources.getInteger(R.integer.grid_layout)
        )
        binding.rv.adapter = mAdapter
    }

    private fun setupRefreshSwipeView() {
        // TODO decor swipe refresh view
        binding.srl.setOnRefreshListener {
            mPresenter.getTopRatedMovies(compositeDisposable)
        }
    }

    override fun startSwipeRefreshView() {
        binding.srl.isRefreshing = true
    }

    override fun stopSwipeRefreshView() {
        binding.srl.isRefreshing = false
    }

    override fun showMovies(result: ArrayList<MoviesRes.Result>) {
        mAdapter.updateItems(result)
    }

    override fun displayServerError(errorMessage: String) {
        Snackbar.make(activity.binding.clMain, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

}
