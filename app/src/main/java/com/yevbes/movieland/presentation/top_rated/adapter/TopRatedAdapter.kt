package com.yevbes.movieland.presentation.top_rated.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes
import android.view.LayoutInflater
import com.yevbes.movieland.databinding.TopRatedMoviesRowBinding


class TopRatedAdapter(
                      private val items: ArrayList<TopRatedMoviesRes.Result>) :
    RecyclerView.Adapter<TopRatedViewHolder>() {

    fun addAllItems(items: List<TopRatedMoviesRes.Result>){
        clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun clear(){
        items.clear()
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