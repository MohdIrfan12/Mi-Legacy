package com.mia.legacy.ui.home.hotstar

import androidx.paging.PagingData
import com.mia.legacy.usecase.Movies.Movie

/**
 * Created by Mohd Irfan on 14/8/21.
 */
interface HotstarViewModelObserver {
    fun showLoader()
    fun hideLoader()
    suspend fun onMoviesFetched(movies: PagingData<Movie>);
    fun onMoviesFetchFailed(message: String?);
}