package com.mia.legacy.ui.onboarding.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mia.legacy.usecase.SaveUserInfoUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserInfoViewModel(private val saveUserInfoUseCase: SaveUserInfoUseCase) : ViewModel() {

    private var mObserver: UserInfoViewModelObserver? = null

    fun onStart(observer: UserInfoViewModelObserver?) {
        this.mObserver = observer
    }

    fun onStop() {
        this.mObserver = null
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun saveUserInfo(userName: String?, userPassword: String?) = viewModelScope.launch {
        saveAndNotify(userName, userPassword)
    }

    private suspend fun saveAndNotify(userName: String?, userPassword: String?) {
        if (userName.isNullOrEmpty()) {
            mObserver?.displayErrorUserNameCanNotBeEmpty()
        } else if (userPassword.isNullOrEmpty()) {
            mObserver?.displayErrorPasswordCanNotBeEmpty()
        } else {
            mObserver?.showLoader()
            saveUserInfoUseCase.saveUserInfo(userName, userPassword)
            mObserver?.hideLoader()
            mObserver?.onUserInfoSaved()
        }
    }
}