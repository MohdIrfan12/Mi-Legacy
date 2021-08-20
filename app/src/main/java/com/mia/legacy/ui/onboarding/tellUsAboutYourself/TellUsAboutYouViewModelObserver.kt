package com.mia.legacy.ui.onboarding.tellUsAboutYourself


interface TellUsAboutYouViewModelObserver {

    fun showLoader()
    fun hideLoader()
    fun onUserInfoSaved();
    fun onSaveUserInfoSaveFailed();
}