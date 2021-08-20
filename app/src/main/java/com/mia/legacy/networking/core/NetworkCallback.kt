package com.mia.legacy.networking.core



/**
 * EndpointCallback
 */
interface NetworkCallback {
    fun onSuccess(apiResponse: NetworkResponse.Success?)
    fun onFailure(apiResponse: NetworkResponse.Failure?)
}