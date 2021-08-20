package com.mia.legacy.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mia.legacy.usecase.FetchUserDataUseCase
import com.mia.legacy.usecase.UserData
import com.mia.legacy.usecase.config.FetchMoviesConfigUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlin.concurrent.thread

class SplashViewModel(
    private val mFetchUserDataUseCase: FetchUserDataUseCase,
    private val configUseCase: FetchMoviesConfigUseCase
) : ViewModel() {

    private var mObserver: SplashViewModelObserver? = null

    fun onStart(observer: SplashViewModelObserver) {
        this.mObserver = observer;
    }

    fun onStop() {
        mObserver = null;
        viewModelScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun fetUserData() = viewModelScope.launch(Dispatchers.IO) {
        mFetchUserDataUseCase.getUserInfo()
            .catch {
                notifyUi(null)
            }
            .collect {
                notifyUi(it)
            }
    }

    private suspend fun notifyUi(mUserData: UserData?) {
        if (mUserData == null || mUserData?.userName.isNullOrEmpty()) {
            mObserver?.navigateOntoUserInfoScreen()
            return
        } else {
            configUseCase.getConfig().catch {
                mObserver?.navigateOntoHomeScreen()
            }.collect {
                mObserver?.updateConfig(it)
                mObserver?.navigateOntoHomeScreen()
            }
            return
        }
    }

}