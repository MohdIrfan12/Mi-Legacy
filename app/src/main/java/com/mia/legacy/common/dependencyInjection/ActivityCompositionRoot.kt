package com.mia.legacy.common.dependencyInjection

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.google.gson.Gson
import com.mia.legacy.networking.core.NetworkCall
import com.mia.legacy.storage.StorageFactory

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class ActivityCompositionRoot(
    private val mActivity: FragmentActivity,
    private val mCompositionRoot: CompositionRoot
) {

    private val mStorageFactory: StorageFactory by lazy {
        StorageFactory(getContext())
    }

    fun getNetworkCall(): NetworkCall {
        return mCompositionRoot.getNetworkCall()
    }

    fun getContext(): Context {
        return mActivity
    }

    fun getActivity(): FragmentActivity {
        return mActivity
    }

    fun getStorageFactory(): StorageFactory {
        return mStorageFactory
    }

    fun getGson(): Gson {
        return mCompositionRoot.getGson()

    }
}