package com.yevbes.movieland.presentation.top_rated.adapter

import android.widget.Filter
import com.yevbes.movieland.presentation.top_rated.model.res.TopRatedMoviesRes

class CustomFilter(
    private val rowItemsFilter: ArrayList<TopRatedMoviesRes.Result>,
    private val topRatedAdapter: TopRatedAdapter
) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = Filter.FilterResults()
        var const = constraint
        //CHECK CONSTRAINT VALIDITY
        if (const != null && const.isNotEmpty()) {
            //CHANGE TO UPPER
            const = const.toString().toUpperCase()
            //STORE OUR FILTERED ITEMS
            val filteredItems = ArrayList<TopRatedMoviesRes.Result>()

            for (i in 0 until rowItemsFilter.size) {
                //CHECK
                val item = rowItemsFilter[i]
                if (item.title.toUpperCase().contains(const) ||
                    item.originalTitle.toUpperCase().contains(const)) {
                    //ADD ITEM TO FILTERED ITEMS
                    filteredItems.add(item)
                }

            }

            results.count = filteredItems.size
            results.values = filteredItems
        } else {
            results.count = rowItemsFilter.size
            results.values = rowItemsFilter
        }

        return results

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        @Suppress("UNCHECKED_CAST")
        topRatedAdapter.updateItemsFilterable(results?.values as ArrayList<TopRatedMoviesRes.Result>)
    }
}