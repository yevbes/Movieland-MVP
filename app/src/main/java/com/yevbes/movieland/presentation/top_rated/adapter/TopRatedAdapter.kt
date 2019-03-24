package com.yevbes.movieland.presentation.top_rated.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.yevbes.movieland.databinding.TopRatedMoviesRowBinding
import com.yevbes.movieland.data.res.MoviesRes


class TopRatedAdapter(
    private var items: ArrayList<MoviesRes.Result>) :
    RecyclerView.Adapter<TopRatedViewHolder>(), Filterable {

    private lateinit var mFilter: CustomFilter

    // for CustomFilter
    private var rowItemsFilter: ArrayList<MoviesRes.Result> = ArrayList()


    override fun getFilter(): Filter {
        if (!::mFilter.isInitialized) {
            mFilter = CustomFilter(rowItemsFilter, this@TopRatedAdapter)
        }
        return mFilter
    }

    fun addAllItems(items: List<MoviesRes.Result>){
        this.items.addAll(items)
        this.rowItemsFilter.addAll(items)
        notifyItemRangeInserted(itemCount,items.size)
    }

    fun updateItems(items: ArrayList<MoviesRes.Result>){
        this.items = items
        this.rowItemsFilter = items
        notifyDataSetChanged()
    }

    fun updateItemsFilterable(items: ArrayList<MoviesRes.Result>){
        this.items = items
        notifyDataSetChanged()
    }


    fun clearAdapter(){
        items.clear()
        rowItemsFilter.clear()
        notifyItemRangeRemoved(0,itemCount)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopRatedViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val rowBinding = TopRatedMoviesRowBinding.inflate(layoutInflater,p0,false)
        return TopRatedViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: TopRatedViewHolder, p1: Int) {
        val item = items[p1]
        p0.bind(item)
    }
}