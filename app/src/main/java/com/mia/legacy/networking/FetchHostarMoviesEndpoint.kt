package com.mia.legacy.networking

import com.mia.legacy.networking.core.NetworkCall
import com.mia.legacy.networking.core.NetworkCallback
import com.mia.legacy.networking.core.NetworkResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.NullPointerException
import java.lang.RuntimeException
import kotlin.coroutines.resumeWithException

/**
 * Created by Mohd Irfan on 14/8/21.
 */
class FetchHostarMoviesEndpoint(private val mNetworkCall: NetworkCall) {

    private val URL = "https://api.themoviedb.org/3/search/movie?"

    suspend fun fetchMovies(page: Int) =
        suspendCancellableCoroutine<NetworkResponse.Success?> { continuation ->
            val params = hashMapOf<String, String>()
            params.put("api_key", "a901f305e2f5b730d20647ff9b996d1a")
            params.put("query", "action")
            params.put("page", page.toString())
            mNetworkCall.get(URL, null, params, object : NetworkCallback {
                override fun onSuccess(apiResponse: NetworkResponse.Success?) {
                    continuation.resumeWith(Result.success(apiResponse));
                }

                override fun onFailure(apiResponse: NetworkResponse.Failure?) {
                    continuation.cancel()
                    if (apiResponse == null) {
                        continuation.resumeWithException(NullPointerException())
                    } else {
                        val runtimeException = RuntimeException(apiResponse.responseMessage);
                        continuation.resumeWithException(RuntimeException(runtimeException))
                    }
                }
            })
            continuation.invokeOnCancellation { }
        }
}