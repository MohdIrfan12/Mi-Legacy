package com.mia.legacy.usecase.config

import com.google.gson.Gson
import com.mia.legacy.networking.FetchMoviesConfigEndpoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Created by Mohd Irfan on 14/8/21.
 */
open class FetchMoviesConfigUseCase(
    private val endpoint: FetchMoviesConfigEndpoint,
    private val gson: Gson
) {

    open fun getConfig() = endpoint.fetchConfig().map {
        gson.fromJson(it?.result, MovieConfig::class.java)
    }
}