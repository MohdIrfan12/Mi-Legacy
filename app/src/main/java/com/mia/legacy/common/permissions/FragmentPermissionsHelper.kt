package com.mia.mvvvmcarchitecture.common.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mia.mvvvmcarchitecture.common.observer.BaseObservable

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class FragmentPermissionsHelper(private val mFragment: Fragment) :
    BaseObservable<FragmentPermissionsHelper.Listener>() {

    interface Listener {
        fun onPermissionGranted(permission: String, requestCode: Int)
        fun onPermissionDeclined(permission: String, requestCode: Int)
        fun onPermissionDeclinedDontAskAgain(permission: String, requestCode: Int)
    }

    fun checkAndRequestPremission(permission: String, requestCode: Int) {
        if (hasPermission(permission)) {
            for (listener in getObserver()) {
                listener.onPermissionGranted(permission, requestCode)
            }
        } else {
            requestPermission(permission, requestCode)
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        mFragment.requestPermissions(arrayOf(permission), requestCode)
    }

    fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (permissions.size < 1) {
            throw RuntimeException("no permissions on request result")
        }
        val permission = permissions[0]
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            notifyPermissionGranted(permission, requestCode)
        } else {
            if (shouldShowRequestPermissionRationale(mFragment.requireActivity(), permission)) {
                notifyPermissionDeclined(permission, requestCode)
            } else {
                notifyPermissionDeclinedDontAskAgain(permission, requestCode)
            }
        }
    }

    private fun notifyPermissionDeclinedDontAskAgain(permission: String, requestCode: Int) {
        for (listener in getObserver()) {
            listener.onPermissionDeclinedDontAskAgain(permission, requestCode)
        }
    }

    private fun notifyPermissionDeclined(permission: String, requestCode: Int) {
        for (listener in getObserver()) {
            listener.onPermissionDeclined(permission, requestCode)
        }
    }

    private fun notifyPermissionGranted(permission: String, requestCode: Int) {
        for (listener in getObserver()) {
            listener.onPermissionGranted(permission, requestCode)
        }
    }

    private fun getContext(): Context {
        return mFragment.requireContext()
    }
}