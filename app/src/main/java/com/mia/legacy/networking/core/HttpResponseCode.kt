package com.mia.legacy.networking.core

/**
 * HttpResponseCode
 */
internal interface HttpResponseCode {
    companion object {
        const val SUCCESS = 200
        const val SUCCESS2 = 299
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val PAGE_NOT_FOUND = 404
        const val INTERNAL_SERVER_ERROR = 500
        const val SERVER_UNREACHABLE = 503
        const val DEFAULT = 1000
    }
}