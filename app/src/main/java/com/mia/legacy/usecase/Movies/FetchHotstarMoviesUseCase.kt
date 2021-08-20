package com.mia.legacy.usecase.Movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import com.mia.legacy.networking.FetchHostarMoviesEndpoint

/**
 * Created by Mohd Irfan on 14/8/21.
 */
open class FetchHotstarMoviesUseCase(
    private val fetchMoviesEndpoint: FetchHostarMoviesEndpoint,
    private val gson: Gson
) :
    PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key?.plus(1) ?: 1
            val response = fetchMovies(nextPageNumber)
            LoadResult.Page(response.results, null, response.page)

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun fetchMovies(page: Int): FetchMovieApiResponse {
        val response = fetchMoviesEndpoint.fetchMovies(page)
        return gson.fromJson(response?.result, FetchMovieApiResponse::class.java)
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        if (state.anchorPosition == null) {
            return null
        } else {
            val page = state.closestPageToPosition(state.anchorPosition!!)
            return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}