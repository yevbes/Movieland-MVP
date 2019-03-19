package com.yevbes.movieland.presentation.most_popular.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yevbes.movieland.R


/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_movies, container, false)
    }


}
