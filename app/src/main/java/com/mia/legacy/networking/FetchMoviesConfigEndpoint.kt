package com.mia.legacy.networking

import com.mia.legacy.networking.core.NetworkCall
import com.mia.legacy.networking.core.NetworkCallback
import com.mia.legacy.networking.core.NetworkResponse
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * Created by Mohd Irfan on 14/8/21.
 */
open class FetchMoviesConfigEndpoint(private val mNetworkCall: NetworkCall) {

    private val URL = "https://api.themoviedb.org/3/configuration?"


   open fun fetchConfig() = callbackFlow<NetworkResponse.Success?> {
        val params = hashMapOf<String, String>()
        params.put("api_key", "a901f305e2f5b730d20647ff9b996d1a")
        mNetworkCall.get(URL, null, params, object : NetworkCallback {
            override fun onSuccess(apiResponse: NetworkResponse.Success?) {
                offer(apiResponse)
            }

            override fun onFailure(apiResponse: NetworkResponse.Failure?) {
                cancel(apiResponse?.responseMessage?:"Runtime Error")
            }
        })
        awaitClose { }
    }
}