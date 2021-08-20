package com.mia.legacy.ui.common.binders

import androidx.appcompat.app.AppCompatActivity
import com.mia.legacy.common.dependencyInjection.ActivityCompositionRoot
import com.mia.legacy.common.dependencyInjection.PresentationCompositionRoot
import com.mia.legacy.MiApplication

abstract class BaseActivity : AppCompatActivity() {

    protected val mActivityCompositionRoot by lazy {
        ActivityCompositionRoot(this, (application as MiApplication).getCompositionRoot())
    }

    protected val mPresentationCompositionRoot by lazy {
        PresentationCompositionRoot(mActivityCompositionRoot)
    }

}