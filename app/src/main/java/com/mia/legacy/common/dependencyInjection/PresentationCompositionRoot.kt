package com.mia.legacy.common.dependencyInjection

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.Gson
import com.mia.legacy.networking.EndpointFactory
import com.mia.legacy.networking.core.NetworkCall
import com.mia.legacy.storage.StorageFactory
import com.mia.legacy.ui.common.dialogs.DialogsManager
import com.mia.legacy.ui.common.screensnavigator.ScreenNavigator
import com.mia.legacy.ui.common.toasthelper.ToastHelper
import com.mia.legacy.ui.common.viewmodel.ViewModelProviderFactory
import com.mia.legacy.usecase.UsecaseFactory
import com.mia.mvvvmcarchitecture.common.permissions.ActivityPermissionsHelper
import com.mia.mvvvmcarchitecture.common.permissions.FragmentPermissionsHelper
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressDispatcher

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class PresentationCompositionRoot(private val mActivityCompositionRoot: ActivityCompositionRoot) {

    private val mActivityPermissionsHelper by lazy {
        ActivityPermissionsHelper(getActivity())
    }

    private val mUsecaseFactory by lazy {
        UsecaseFactory(getEndpointFactory(), getStorageFactory(), getGson())
    }

    private val mToastHelper by lazy {
        ToastHelper(getContext())
    }

    private var mFragmentPermissionsHelper: FragmentPermissionsHelper? = null

    fun getNetworkCall(): NetworkCall {
        return mActivityCompositionRoot.getNetworkCall()
    }

    private fun getContext(): Context {
        return mActivityCompositionRoot.getContext()
    }

    private fun getActivity(): FragmentActivity {
        return mActivityCompositionRoot.getActivity()
    }

    private fun getFragmentManager(): FragmentManager {
        return getActivity().supportFragmentManager
    }

    private fun getLayoutInflator(): LayoutInflater {
        return LayoutInflater.from(getContext())
    }

    private fun getEndpointFactory(): EndpointFactory {
        return EndpointFactory(getNetworkCall())
    }

    fun getScreenNavigatior(): ScreenNavigator {
        return ScreenNavigator(getActivity())
    }

    internal fun getToastHelper(): ToastHelper {
        return mToastHelper
    }

    fun getBackPressDispatcher(): BackPressDispatcher {
        return getActivity() as BackPressDispatcher
    }

    fun getDialogsManager(): DialogsManager {
        return DialogsManager(getContext(), getFragmentManager())
    }

    fun getFragmentPermissionsHelper(mFragment: Fragment): FragmentPermissionsHelper {
        if (mFragmentPermissionsHelper == null) {
            mFragmentPermissionsHelper = FragmentPermissionsHelper(mFragment)
        }
        return mFragmentPermissionsHelper!!
    }

    fun getActivityPermissionsHelper(): ActivityPermissionsHelper {
        return mActivityPermissionsHelper
    }

    fun getUsecaseFactory(): UsecaseFactory {
        return mUsecaseFactory
    }

    fun getViewModelProviderFactory(owner: SavedStateRegistryOwner): ViewModelProviderFactory {
        return ViewModelProviderFactory(owner, null, getUsecaseFactory());
    }

    fun getStorageFactory(): StorageFactory {
        return mActivityCompositionRoot.getStorageFactory()
    }

    private fun getGson(): Gson {
        return mActivityCompositionRoot.getGson()
    }
}