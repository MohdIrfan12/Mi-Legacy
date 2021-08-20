package com.mia.legacy.ui.common.dialogs

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mia.legacy.R
import com.mia.legacy.ui.common.dialogs.infodialog.InfoDialog
import com.mia.legacy.ui.common.dialogs.progressDialog.ProgressDialog

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class DialogsManager(private val mContext: Context, private val mFragmentManager: FragmentManager) {

    fun showProgressDialog(@Nullable tag: String?) {
        val mInfoDialog = ProgressDialog.newInstance()
        mInfoDialog.show(mFragmentManager, tag)
    }

    fun showErrorInfoPrompt(@Nullable tag: String?) {
        val mInfoDialog = InfoDialog.newInstance(
            getString(R.string.text_error_promt_title),
            getString(R.string.text_network_call_failed),
            getString(R.string.text_ok)
        )
        mInfoDialog.show(mFragmentManager, tag)
    }

    fun dismissDialog(@Nullable tag: String?) {
        var dialog = mFragmentManager.findFragmentByTag(tag)
        if (dialog != null && dialog is DialogFragment) {
            dialog.dismiss()
            dialog = null
        }
    }

    private fun getString(@StringRes stringId: Int): String {
        return mContext.getString(stringId)
    }
}