package com.mia.legacy.ui.onboarding.profilepic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UploadProfilePicViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var mObserver: UploadProfilePicViewModelObserver? = null
    private val DELAY_TIME: Long = 3 * 1000;

    fun onStart(observer: UploadProfilePicViewModelObserver?) {
        this.mObserver = observer
    }

    fun onStop() {
        this.mObserver = null
    }

    fun saveProfilePic() {
        viewModelScope.launch {
            delay(DELAY_TIME)
            mObserver?.profilePicUploadSuccess()
        }
    }
}