package com.mia.legacy.ui.common.dialogs.progressDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.mia.legacy.databinding.DialogInfoBinding
import com.mia.legacy.databinding.DialogProgressBinding
import com.mia.legacy.ui.common.dialogs.BaseDialog

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class ProgressDialog : BaseDialog() {

    companion object {
        fun newInstance() = ProgressDialog()
    }

    private lateinit var binding: DialogProgressBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        binding = DialogProgressBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        return dialog
    }

}