package com.mia.legacy.ui.common.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.mia.legacy.common.dependencyInjection.PresentationCompositionRoot
import com.mia.legacy.ui.home.HomeViewModel
import com.mia.legacy.ui.home.hotstar.HotstarViewModel
import com.mia.legacy.ui.home.netflix.NetflixViewModel
import com.mia.legacy.ui.onboarding.profilepic.UploadProfilePicViewModel
import com.mia.legacy.ui.onboarding.tellUsAboutYourself.TellUsAboutYouViewModel
import com.mia.legacy.ui.onboarding.userinfo.UserInfoViewModel
import com.mia.legacy.ui.splash.SplashViewModel
import com.mia.legacy.usecase.UsecaseFactory
import java.lang.RuntimeException

/**
 * Created by Mohd Irfan on 31/12/20.
 */
class ViewModelProviderFactory(
    owner: SavedStateRegistryOwner, defaultArgs: Bundle?,
    private val usecaseFactory: UsecaseFactory
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {


    override fun <T : ViewModel?> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(usecaseFactory.getFetchUserDataUseCase(),
            usecaseFactory.getFetchMoviesConfigUseCase()) as T

        } else if (modelClass.isAssignableFrom(UserInfoViewModel::class.java)) {
            return UserInfoViewModel(usecaseFactory.getSaveUserInfoUseCase()) as T

        } else if (modelClass.isAssignableFrom(UploadProfilePicViewModel::class.java)) {
            return UploadProfilePicViewModel(handle) as T

        } else if (modelClass.isAssignableFrom(TellUsAboutYouViewModel::class.java)) {
            return TellUsAboutYouViewModel(
                usecaseFactory.getFetchUserInfoUseCase(),
                usecaseFactory.getSaveUserDataUseCase()
            ) as T
        }
        else if (modelClass.isAssignableFrom(HotstarViewModel::class.java)) {
            return HotstarViewModel(usecaseFactory.getFetchMoviesUseCase()) as T
        }

        else if (modelClass.isAssignableFrom(NetflixViewModel::class.java)) {
            return NetflixViewModel(usecaseFactory.getFetchNetflixMoviesUseCase()) as T
        }

        else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        }

        throw RuntimeException("view modeel of type " + modelClass.name + " not available")
    }
}