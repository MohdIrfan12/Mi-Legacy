package com.mia.legacy.networking

import com.mia.legacy.networking.core.NetworkCall


/**
 * Created by Mohd Irfan on 07/01/21.
 */
class EndpointFactory(private val networkCall: NetworkCall) {

    fun getFetchMoviesEndpoint(): FetchHostarMoviesEndpoint {
        return FetchHostarMoviesEndpoint(networkCall)
    }

    fun getMoviesConfigEndpoint(): FetchMoviesConfigEndpoint {
        return FetchMoviesConfigEndpoint(networkCall)
    }

    fun getFetchNetflixMoviesEndpoint(): FetchNetflixMoviesEndpoint {
        return FetchNetflixMoviesEndpoint(networkCall)
    }

}