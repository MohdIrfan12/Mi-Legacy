package com.mia.legacy.ui.onboarding.profilepic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mia.legacy.databinding.UploadProfilePicFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment

class UploadProfilePicFragment : BaseFragment(), UploadProfilePicViewModelObserver,
    View.OnClickListener {


    private lateinit var viewModel: UploadProfilePicViewModel
    private lateinit var binding: UploadProfilePicFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modelFactory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, modelFactory).get(UploadProfilePicViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UploadProfilePicFragmentBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart(this)
        binding.btnContinue.setOnClickListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
        binding.btnContinue.setOnClickListener(null)
    }

    override fun onClick(v: View?) {
        viewModel.saveProfilePic()
    }

    override fun profilePicUploadSuccess() {
        mPresentationCompositionRoot.getScreenNavigatior()
            .uploadProfilePicToTellUsAboutYouFragmentNavigation(findNavController())
    }

    override fun profilePicUploadFailed() {
    }
}