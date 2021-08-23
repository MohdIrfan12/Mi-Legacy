package com.mia.legacy.ui.onboarding.userinfo

import com.mia.legacy.usecase.SaveUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohd Irfan on 18/8/21.
 */
@RunWith(MockitoJUnitRunner::class)
internal class UserInfoViewModelTest {

    private val USER_NAME = "Irfan"
    private val PASSWORD = "1234567890"

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<String>

    @Mock
    private lateinit var mSaveUserInfoUseCase: SaveUserInfoUseCase

    @Mock
    private lateinit var mUserInfoViewModelObserver: UserInfoViewModelObserver

    private lateinit var SUT: UserInfoViewModel
    private val testMainScope = newSingleThreadContext("testThread")

    @Before
    fun setUp() {
        Dispatchers.setMain(testMainScope)
        SUT = UserInfoViewModel(mSaveUserInfoUseCase);
        SUT.onStart(mUserInfoViewModelObserver)
    }

    @Test
    fun saveUserData_success_parameterPassedToUseCase() {
        runBlocking {
            //arrange
            // act
            mSaveUserInfoUseCase.saveUserInfo(USER_NAME, PASSWORD)
            // assert
            Mockito.verify(mSaveUserInfoUseCase).saveUserInfo(capture(argumentCaptor), capture(argumentCaptor))
            val dalaList = argumentCaptor.allValues
            Assert.assertEquals(dalaList.get(0), USER_NAME)
            Assert.assertEquals(dalaList.get(1), PASSWORD)
        }
    }

    @Test
    fun saveUserData_success_NotifyObserber() {
        runBlocking {
            //arrange
            // act
            SUT.saveUserInfo(USER_NAME, PASSWORD).join()
            // assert
            Mockito.verify(mUserInfoViewModelObserver, Mockito.atLeastOnce()).hideLoader()
            Mockito.verify(mUserInfoViewModelObserver).onUserInfoSaved()
        }
    }

    @Test
    fun saveUserData_failure_displayUserNameBlankError() {
        runBlocking {
            //arrange
            // act
            SUT.saveUserInfo("", "").join()
            // assert
            Mockito.verify(mUserInfoViewModelObserver).displayErrorUserNameCanNotBeEmpty()
        }
    }

    @Test
    fun saveUserData_failure_displayPasswordBlankError() {
        runBlocking {
            //arrange
            // act
            SUT.saveUserInfo(USER_NAME, "").join()
            // assert
            Mockito.verify(mUserInfoViewModelObserver).displayErrorPasswordCanNotBeEmpty()
        }
    }

    @Test
    fun nonFunctional() {
        runBlocking {
            //arrange
            // act
            SUT.saveUserInfo(USER_NAME, PASSWORD).join()
            // assert
            Mockito.verify(mUserInfoViewModelObserver, Mockito.atLeastOnce()).showLoader()
            Mockito.verify(mUserInfoViewModelObserver, Mockito.atLeastOnce()).hideLoader()
        }
    }

    @After
    fun tearDown() {
        SUT.onStop()
        Dispatchers.resetMain()
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}
