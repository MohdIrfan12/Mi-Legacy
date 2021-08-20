package com.mia.legacy.ui.common.binders

import androidx.fragment.app.Fragment
import com.mia.legacy.common.dependencyInjection.ActivityCompositionRoot
import com.mia.legacy.common.dependencyInjection.PresentationCompositionRoot
import com.mia.legacy.MiApplication

abstract class BaseFragment : Fragment() {

    protected val mActivityCompositionRoot by lazy {
        ActivityCompositionRoot(
            requireActivity(),
            (requireActivity().application as MiApplication).getCompositionRoot()
        )
    }

    protected val mPresentationCompositionRoot by lazy {
        PresentationCompositionRoot(mActivityCompositionRoot)
    }
}