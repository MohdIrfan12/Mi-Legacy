package com.mia.legacy.ui.onboarding.tellUsAboutYourself

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mia.legacy.databinding.TellUsAboutYouFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment
import com.mia.legacy.ui.common.dialogs.DialogsManager

class TellUsAboutYouFragment : BaseFragment(), TellUsAboutYouViewModelObserver,
    View.OnClickListener {

    private val TAG = "TellUsAboutYouFragment"
    private val PROGRESS_INDICATOR_TAG = "Progress_Indicator"
    private lateinit var binding: TellUsAboutYouFragmentBinding
    private lateinit var viewModel: TellUsAboutYouViewModel
    private lateinit var mDialogManager: DialogsManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        mDialogManager = mPresentationCompositionRoot.getDialogsManager();
        val modelFactory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, modelFactory).get(TellUsAboutYouViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        binding = TellUsAboutYouFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
        viewModel.onStart(this)
        binding.btnContinue.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
        viewModel.onStop()
        binding.btnContinue.setOnClickListener(null)
    }

    override fun onClick(v: View?) {
        val profession = binding.etProfession.text.toString()
        val summary = binding.etSummary.text.toString()
        viewModel.saveData(profession, summary)
    }

    override fun showLoader() {
        mDialogManager.showProgressDialog(PROGRESS_INDICATOR_TAG)
    }

    override fun hideLoader() {
        mDialogManager.dismissDialog(PROGRESS_INDICATOR_TAG)
    }

    override fun onUserInfoSaved() {
        mPresentationCompositionRoot.getScreenNavigatior().navigateOntoHomeScreen()
        requireActivity().finish()
    }

    override fun onSaveUserInfoSaveFailed() {
        mPresentationCompositionRoot.getToastHelper().showGeneralError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("===onSaveInstanceState",TAG)
    }
}