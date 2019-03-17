package com.yevbes.movieland.interfaces

import android.view.View

interface OnItemClickListener {
    fun onClick(view: View, position: Int): Any
}