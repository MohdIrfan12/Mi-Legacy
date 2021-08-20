package com.mia.legacy.usecase.Movies


import com.google.gson.Gson
import com.mia.legacy.networking.FetchNetflixMoviesEndpoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

/**
 * Created by Mohd Irfan on 14/8/21.
 */
class FetchNetflixMoviesUseCase(
    private val fetchMoviesEndpoint: FetchNetflixMoviesEndpoint,
    private val gson: Gson
) {

    private var page: Int? = 1;

    fun fetchMovies(scope: CoroutineScope) = fetchMoviesEndpoint.fetchMovies(page ?: 1).map {
        val response = gson.fromJson(it?.result, FetchMovieApiResponse::class.java)
        page = response.page
        response.results
    }.shareIn(scope, SharingStarted.WhileSubscribed(), 1)
}