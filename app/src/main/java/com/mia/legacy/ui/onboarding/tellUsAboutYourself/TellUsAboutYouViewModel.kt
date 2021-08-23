package com.mia.legacy.ui.onboarding.tellUsAboutYourself

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mia.legacy.usecase.FetchUserInfoUseCase
import com.mia.legacy.usecase.SaveUserDataUseCase
import com.mia.legacy.usecase.UserData
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TellUsAboutYouViewModel(
    private val mFetchUserInfoUseCase: FetchUserInfoUseCase,
    private val mSaveTellUsAboutYouUseCase: SaveUserDataUseCase,
) :
    ViewModel() {

    private var mObserver: TellUsAboutYouViewModelObserver? = null
    private val DELAY_TIME: Long = 2 * 1000;


    fun onStart(observer: TellUsAboutYouViewModelObserver?) {
        this.mObserver = observer
    }

    fun onStop() {
        this.mObserver = null
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun saveData(profession: String?, summary: String?) = viewModelScope.launch {
        mObserver?.showLoader()
        fetchUserInfo(profession, summary)
    }

    private suspend fun fetchUserInfo(profession: String?, summary: String?) {
        mFetchUserInfoUseCase.fetchUserInfo()
            .catch { exception ->
                exception.printStackTrace()
                mObserver?.hideLoader()
                mObserver?.onSaveUserInfoSaveFailed()
            }
            .collect {
                it.profession = profession
                it.summary = summary
                saveUserDataAndNotify(it)
            }
    }

    private suspend fun saveUserDataAndNotify(userData: UserData) {
        mSaveTellUsAboutYouUseCase.saveUserInfo(userData)
        mObserver?.hideLoader()
        mObserver?.onUserInfoSaved()
    }
}