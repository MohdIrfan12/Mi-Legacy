package com.mia.legacy.ui.home.hotstar

import androidx.recyclerview.widget.DiffUtil
import com.mia.legacy.usecase.Movies.Movie

/**
 * Created by Mohd Irfan on 15/8/21.
 */
class HostStarMoviesAdapterPageComparator : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
         // Id is unique.
        return oldItem.poster_path == newItem.poster_path
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}