package com.mia.legacy.networking.core

/**
 * Created by Mohd Irfan on 14/8/21.
 */
sealed class NetworkResponse {

    data class Success(val result: String) : NetworkResponse()
    data class Failure(val responseMessage: String, val responseCode: Int) : NetworkResponse()
}