package com.mia.mvvvmcarchitecture.common.permissions

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.mia.mvvvmcarchitecture.common.observer.BaseObservable

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class ActivityPermissionsHelper(private val mActivity: FragmentActivity) :
    BaseObservable<ActivityPermissionsHelper.Listener>() {

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
        return ContextCompat.checkSelfPermission(
            mActivity, permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(mActivity, arrayOf(permission), requestCode)
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
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
}