package com.mia.legacy.networking.core

import android.os.Handler
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.ExecutorService

/**
 * EndpointImpl
 */
class NetworkCallImpl(
    private val service: ExecutorService,
    private val mainThreadHadler: Handler
) : NetworkCall {

    companion object {
        private const val GET = "GET"
        private const val POST = "POST"
        private const val PUT = "PUT"
        private const val DELETE = "DELETE"
        private const val utf_8_encoding = "utf-8"
    }

    override fun get(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    ) {
        service.execute {
            var connection: HttpURLConnection? = null
            var networkResponse: NetworkResponse? = null
            try {
                val reqBody = convertReqBodyToString(reqParam)
                connection = if (reqBody.isNullOrEmpty()) {
                    getUrlConnection(url, header, GET)
                } else {
                    getUrlConnection(url + reqBody, header, GET)
                }
                networkResponse = readResponse(connection, null)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            notifyResult(networkResponse, listener)
        }
    }

    override fun post(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    ) {
        service.execute {
            var networkResponse: NetworkResponse? = null
            var connection: HttpURLConnection? = null
            try {
                connection = getUrlConnection(url, header, POST)
                val reqBody = convertReqBodyToString(reqParam)
                if (!reqBody.isNullOrEmpty()) {
                    val outputStream = connection.outputStream
                    val input = reqBody.toByteArray(charset(utf_8_encoding))
                    outputStream.write(input, 0, input.size)
                    outputStream.close()
                    outputStream.flush()
                }
                networkResponse = readResponse(connection, utf_8_encoding)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            notifyResult(networkResponse, listener)
        }
    }

    override fun put(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    ) {
        service.execute {
            var networkResponse: NetworkResponse? = null
            var connection: HttpURLConnection? = null
            try {
                connection = getUrlConnection(url, header, PUT)
                val reqBody = convertReqBodyToString(reqParam)
                if (!reqBody.isNullOrEmpty()) {
                    val outputStream = connection.outputStream
                    val input = reqBody.toByteArray(charset(utf_8_encoding))
                    outputStream.write(input, 0, input.size)
                    outputStream.close()
                    outputStream.flush()
                }
                networkResponse = readResponse(connection, utf_8_encoding)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            notifyResult(networkResponse, listener)
        }
    }

    override fun delete(
        url: String,
        header: HashMap<String, String>?,
        reqParam: HashMap<String, String>?,
        listener: NetworkCallback
    ) {
        service.execute {
            var networkResponse: NetworkResponse? = null
            var connection: HttpURLConnection? = null
            try {
                val reqBody = convertReqBodyToString(reqParam)
                connection = if (reqBody.isNullOrEmpty()) {
                    getUrlConnection(url, header, DELETE)
                } else {
                    getUrlConnection(url + reqBody, header, DELETE)
                }
                networkResponse = readResponse(connection, null)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            notifyResult(networkResponse, listener)
        }
    }

    private fun getUrlConnection(
        endpointurl: String,
        header: HashMap<String, String>?,
        reqMethod: String
    ): HttpURLConnection {
        val url = URL(endpointurl)
        val connection = url.openConnection() as HttpURLConnection
        if (reqMethod == POST || reqMethod == PUT) {
            connection.doOutput = true
        }
        connection.doInput = true
        connection.useCaches = true
        connection.connectTimeout = 60000
        connection.readTimeout = 60000
        connection.setChunkedStreamingMode(0)
        connection.requestMethod = reqMethod

        if (!header.isNullOrEmpty()) {
            for (item in header.entries) {
                connection.setRequestProperty(item.key, item.value)
            }
        }
        Log.d("NetworkCallImpl", endpointurl)
        return connection
    }

    private fun convertReqBodyToString(reqParam: HashMap<String, String>?): String? {
        if (!reqParam.isNullOrEmpty()) {
            val query = StringBuilder()
            // adding req params
            for (item in reqParam.entries) {
                try {
                    query.append(item.key).append("=")
                        .append(URLEncoder.encode(item.value, utf_8_encoding))
                    query.append("&")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            val lastIndex = query.length - 1
            if (query[lastIndex] == '&') {
                query.deleteCharAt(lastIndex)
            }
            return query.toString()
        }
        return null
    }

    private fun readResponse(
        connection: HttpURLConnection?,
        standardCharset: String?
    ): NetworkResponse {
        val responseCode = connection!!.responseCode
        var reader: BufferedReader? = null

        if (standardCharset.isNullOrEmpty()) {
            reader = if (responseCode > HttpResponseCode.SUCCESS2) {
                BufferedReader(InputStreamReader(connection.errorStream))
            } else {
                BufferedReader(InputStreamReader(connection.inputStream))
            }
        } else {
            reader = if (responseCode > HttpResponseCode.SUCCESS2) {
                BufferedReader(InputStreamReader(connection.errorStream, standardCharset))
            } else {
                BufferedReader(InputStreamReader(connection.inputStream, standardCharset))
            }
        }
        val response = StringBuilder()
        var responseLine: String? = null
        while (reader.readLine().also { responseLine = it } != null) {
            response.append(responseLine?.trim());
        }
        Log.d("NetworkCallImpl", response.toString())
        if (connection.responseCode <= HttpResponseCode.SUCCESS2) {
            return NetworkResponse.Success(response.toString())
        } else {
            return NetworkResponse.Failure(connection.responseMessage, connection.responseCode)
        }
    }

    private fun notifyResult(apiResponse: NetworkResponse?, listener: NetworkCallback) {
       mainThreadHadler.post {
           when (apiResponse) {
               is NetworkResponse.Failure -> listener.onFailure(apiResponse)
               is NetworkResponse.Success -> listener.onSuccess(apiResponse)
           }
       }
    }
}