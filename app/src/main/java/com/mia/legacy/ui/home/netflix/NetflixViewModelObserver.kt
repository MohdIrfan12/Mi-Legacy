package com.mia.legacy.ui.home.netflix

import com.mia.legacy.usecase.Movies.Movie

/**
 * Created by Mohd Irfan on 14/8/21.
 */
interface NetflixViewModelObserver {
    fun showLoader()
    fun hideLoader()
    suspend fun onMoviesFetched(movies: List<Movie>);
    fun onMoviesFetchFailed(message: String?);
}