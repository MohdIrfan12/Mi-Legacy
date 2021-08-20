package com.mia.legacy.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mia.legacy.MiApplication
import com.mia.legacy.databinding.SplashFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment
import com.mia.legacy.ui.common.screensnavigator.ScreenNavigator
import com.mia.legacy.usecase.config.MovieConfig

class SplashFragment : BaseFragment(), SplashViewModelObserver {


    private lateinit var mScreenNavigator: ScreenNavigator
    private lateinit var viewModel: SplashViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modelFactory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, modelFactory).get(SplashViewModel::class.java)

        mScreenNavigator = mPresentationCompositionRoot.getScreenNavigatior();
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SplashFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart(this)
        viewModel.fetUserData()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun navigateOntoUserInfoScreen() {
        mScreenNavigator.splashToUserInfoNavigation(findNavController())
    }

    override fun navigateOntoHomeScreen() {
        mScreenNavigator.navigateOntoHomeScreen()
        requireActivity().finish()
    }

    override fun updateConfig(config: MovieConfig?) {
        config?.let { (requireActivity().application as MiApplication).setMovieConfig(it) }
    }
}