package com.mia.legacy.usecase.config

/**
 * Created by Mohd Irfan on 14/8/21.
 */
data class MovieConfig(val images: Images)
data class Images(val base_url: String, val poster_sizes: List<String>)