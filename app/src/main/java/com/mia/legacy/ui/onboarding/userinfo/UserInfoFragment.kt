package com.mia.legacy.ui.onboarding.userinfo

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mia.legacy.databinding.UserInfoFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment
import com.mia.legacy.ui.common.dialogs.DialogsManager

class UserInfoFragment : BaseFragment(), UserInfoViewModelObserver, View.OnClickListener {

    private val TAG = "UserInfoFragment"
    private val PROGRESS_INDICATOR_TAG = "Progress_Indicator"
    private lateinit var viewModel: UserInfoViewModel
    private lateinit var binding: UserInfoFragmentBinding
    private lateinit var mDialogManager: DialogsManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        val modelFactory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, modelFactory).get(UserInfoViewModel::class.java)
        mDialogManager = mPresentationCompositionRoot.getDialogsManager();
     }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        binding = UserInfoFragmentBinding.inflate(inflater, container, false);
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
        val userName = binding.etUserName.text.toString()
        val userPassword = binding.etPassword.text.toString()
        viewModel.saveUserInfo(userName, userPassword)
    }

    override fun showLoader() {
        mDialogManager.showProgressDialog(PROGRESS_INDICATOR_TAG)
    }

    override fun hideLoader() {
        mDialogManager.dismissDialog(PROGRESS_INDICATOR_TAG)
    }

    override fun displayErrorUserNameCanNotBeEmpty() {
        mPresentationCompositionRoot.getToastHelper().showEmptyUserNameError()
    }

    override fun displayErrorPasswordCanNotBeEmpty() {
        mPresentationCompositionRoot.getToastHelper().showEmptyPasswordError()
    }

    override fun onUserInfoSaved() {
        mPresentationCompositionRoot.getScreenNavigatior()
            .userInfoToTellUsAboutYouFragmentNavigation(findNavController())
    }

    override fun onSaveUserInfoSaveFailed() {
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