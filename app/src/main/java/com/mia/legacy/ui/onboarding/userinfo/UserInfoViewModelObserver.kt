package com.mia.legacy.ui.onboarding.userinfo


interface UserInfoViewModelObserver {

    fun onUserInfoSaved();

    fun onSaveUserInfoSaveFailed();
    fun displayErrorUserNameCanNotBeEmpty()
    fun displayErrorPasswordCanNotBeEmpty()
    fun showLoader()
    fun hideLoader()
}