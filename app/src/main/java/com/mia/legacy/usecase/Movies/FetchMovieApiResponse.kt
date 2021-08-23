package com.mia.legacy.usecase.Movies

/**
 * Created by Mohd Irfan on 14/8/21.
 */
data class FetchMovieApiResponse(
    val page: Int?,
    val total_pages: Int?,
    val results: List<Movie>
)