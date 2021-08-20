package com.mia.legacy.ui.common.dialogs

import androidx.fragment.app.DialogFragment
import com.mia.legacy.common.dependencyInjection.ActivityCompositionRoot
import com.mia.legacy.common.dependencyInjection.PresentationCompositionRoot
import com.mia.legacy.MiApplication

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
abstract class BaseDialog : DialogFragment() {

    protected val mActivityCompositionRoot by lazy {
        ActivityCompositionRoot(
            requireActivity(),
            (requireActivity().application as MiApplication).getCompositionRoot()
        )
    }

    protected val mControllerCompositionRoot by lazy {
        PresentationCompositionRoot(mActivityCompositionRoot)
    }
}