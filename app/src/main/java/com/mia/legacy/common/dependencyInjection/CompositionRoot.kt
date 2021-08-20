package com.mia.legacy.common.dependencyInjection

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.mia.legacy.networking.core.NetworkCall
import com.mia.legacy.networking.core.NetworkCallImpl
import com.mia.legacy.storage.StorageFactory
import java.util.concurrent.Executors

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class CompositionRoot {

    private val mExecutorService by lazy {
        Executors.newFixedThreadPool(4)
    }

    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper());
    }

    private val mNetworkCall: NetworkCall by lazy {
        NetworkCallImpl(mExecutorService, mainThreadHandler)
    }

    private val mGson: Gson by lazy {
        Gson()
    }

    fun getNetworkCall(): NetworkCall {
        return mNetworkCall
    }

    fun getGson(): Gson {
        return mGson
    }
}