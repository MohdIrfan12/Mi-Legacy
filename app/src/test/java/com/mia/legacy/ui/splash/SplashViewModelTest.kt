package com.mia.legacy.ui.splash

import com.google.gson.Gson
import com.mia.legacy.networking.FetchMoviesConfigEndpoint
import com.mia.legacy.usecase.FetchUserDataUseCase
import com.mia.legacy.usecase.UserData
import com.mia.legacy.usecase.config.FetchMoviesConfigUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

/**
 * Created by Mohd Irfan on 17/8/21.
 */
@RunWith(MockitoJUnitRunner::class)
internal class SplashViewModelTest {

    @Mock
    private lateinit var mFetchMoviesConfigEndpoint: FetchMoviesConfigEndpoint
    private val mGson = Gson()

    @Mock
    private lateinit var mSplashViewModelObserver: SplashViewModelObserver

    private lateinit var configUseCase: FetchMoviesConfigUseCase

    @Mock
    private lateinit var mFetchUserDataUseCase: FetchUserDataUseCase

    private lateinit var SUT: SplashViewModel;
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        configUseCase = FetchMoviesConfigUseCase(mFetchMoviesConfigEndpoint, mGson)
        Dispatchers.setMain(mainThreadSurrogate)
        SUT = SplashViewModel(mFetchUserDataUseCase, configUseCase)
        SUT.onStart(mSplashViewModelObserver)
    }

    @Test
    fun fetchUserData_success_notifyObserberForHomeScreenNavigationWhenUserExist() {
        runBlocking {
            // arrange
            success()
            // act
            SUT.fetUserData().join()
            // Assert
            Mockito.verify(mSplashViewModelObserver, Mockito.atLeast(1)).navigateOntoHomeScreen()
        }
    }

    @Test
    fun fetchUserData_success_notifyObserberForHomeScreenNavigationWhenUserNameIsNull() {
        runBlocking {
            // arrange
            sucesUserNameMissing()
            // act
            SUT.fetUserData().join()
            // Asserty
            Mockito.verify(mSplashViewModelObserver, Mockito.atLeast(1))
                .navigateOntoUserInfoScreen()
        }
    }

    @Test
    @Throws(RuntimeException::class)
    fun fetcUserData_failure_notifyObserberForUserInfoNavigation() {
        runBlocking {
            SUT.fetUserData().join()
            // Asserty
            Mockito.verify(mSplashViewModelObserver, Mockito.atLeast(1))
                .navigateOntoUserInfoScreen()
        }
    }

    private fun success() {
        val userData = UserData("Irfan", "1234567890")
        Mockito.`when`(mFetchUserDataUseCase.getUserInfo()).thenReturn(flowOf(userData))
    }

    private fun sucesUserNameMissing() {
        val userData = UserData()
        Mockito.`when`(mFetchUserDataUseCase.getUserInfo()).thenReturn(flowOf(userData))
    }

    @After
    fun tearDown() {
        SUT.onStop()
        Dispatchers.resetMain()
    }
}
