package com.mia.legacy.networking.core

import java.util.*

/**
 * Endpoints
 */
interface NetworkCall {
    /**
     * http get request
     *
     * @param url      endpoint url
     * @param header   headers if any
     * @param reqParam request parameters if any
     */
    operator fun get(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    )

    /**
     * http post request
     *
     * @param url      endpoint url
     * @param header   headers if any
     * @param reqParam request parameters if any
     */
    fun post(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    )

    /**
     * http put request
     *
     * @param url      endpoint url
     * @param header   headers if any
     * @param reqParam request parameters if any
     */
    fun put(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    )

    /**
     * http delete request
     *
     * @param url      endpoint url
     * @param header   headers if any
     * @param reqParam request parameters if any
     */
    fun delete(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    )
}