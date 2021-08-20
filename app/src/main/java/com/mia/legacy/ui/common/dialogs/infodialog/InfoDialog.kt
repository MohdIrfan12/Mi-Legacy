package com.mia.legacy.ui.common.dialogs.infodialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.mia.legacy.databinding.DialogInfoBinding
import com.mia.legacy.ui.common.dialogs.BaseDialog

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class InfoDialog : BaseDialog() {

    companion object {

        private const val ARG_KEY_TITLE = "ARG_KEY_TITLE"
        private const val ARG_KEY_MESSAGE = "ARG_KEY_MESSAGE"
        private const val ARG_KEY_BUTTON_TEXT = "ARG_KEY_BUTTON_TEXT"

        fun newInstance(title: String, message: String, buttonText: String): InfoDialog {
            val infoDialog = InfoDialog()
            val args = Bundle(3)
            args.putString(ARG_KEY_TITLE, title)
            args.putString(ARG_KEY_MESSAGE, message)
            args.putString(ARG_KEY_BUTTON_TEXT, buttonText)
            infoDialog.setArguments(args)
            return infoDialog
        }
    }

    private lateinit var binding: DialogInfoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        binding = DialogInfoBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        setDialogValuesAndHnadleOkClickEvent()
        return dialog
    }

    private fun setDialogValuesAndHnadleOkClickEvent() {
        arguments?.let {
            binding.tvTitle.setText(it.getString(ARG_KEY_TITLE))
            binding.tvMsg.setText(it.getString(ARG_KEY_MESSAGE))
            binding.btnOk.setText(it.getString(ARG_KEY_BUTTON_TEXT))
        }
        binding.btnOk.setOnClickListener { onButtonClicked() }
    }

    private fun onButtonClicked() {
        dismiss()
    }
}