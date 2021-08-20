package com.mia.legacy.ui.splash

import com.mia.legacy.usecase.config.MovieConfig


interface SplashViewModelObserver {

    fun navigateOntoUserInfoScreen()
    fun navigateOntoHomeScreen()
    fun updateConfig(config: MovieConfig?)
}