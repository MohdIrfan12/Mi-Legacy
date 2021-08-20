package com.mia.legacy.ui.common.toasthelper

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.mia.legacy.R

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ToastHelper(private val mContext: Context) {

    fun showEmptyUserNameError() {
        Toast.makeText(mContext, getString(R.string.text_user_name_can_not_not_be_empty), Toast.LENGTH_SHORT).show()
    }

    fun showEmptyPasswordError() {
        Toast.makeText(mContext, getString(R.string.text_password_can_not_not_be_empty), Toast.LENGTH_SHORT).show()
    }

    fun showGeneralError() {
        Toast.makeText(mContext, getString(R.string.text_general_error), Toast.LENGTH_SHORT).show()
    }

    fun showToast(msg:String?) {
        msg?.let {
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getString(@StringRes stringId: Int): String {
        return mContext.getString(stringId)
    }
}