package com.mia.legacy.ui.onboarding.tellUsAboutYourself

import com.mia.legacy.usecase.FetchUserInfoUseCase
import com.mia.legacy.usecase.SaveUserDataUseCase
import com.mia.legacy.usecase.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
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
internal class TellUsAboutYouViewModelTest {

    private val PROFESSION = "developer"
    private val SUMMARY = "hey, i am a software developer"
    private val data = UserData("Irfan", "1234567890")

    @Captor
    private lateinit var argumentCapter: ArgumentCaptor<UserData>

    @Mock
    private lateinit var mObserver: TellUsAboutYouViewModelObserver

    @Mock
    private lateinit var mFetchUserInfoUseCase: FetchUserInfoUseCase

    @Mock
    private lateinit var mSaveTellUsAboutYouUseCase: SaveUserDataUseCase
    private lateinit var SUT: TellUsAboutYouViewModel

    private val testScope = newSingleThreadContext("")

    @Before
    fun before() {
        Dispatchers.setMain(testScope)
        SUT = TellUsAboutYouViewModel(mFetchUserInfoUseCase, mSaveTellUsAboutYouUseCase)
        SUT.onStart(mObserver)
    }

    @Test
    fun userInfoSaved_success_notifyDataSaved() {
        runBlocking {
            // arange
            Mockito.`when`(mFetchUserInfoUseCase.fetchUserInfo()).thenReturn(flowOf(data))
            // act
            SUT.saveData(PROFESSION, SUMMARY).join()
            // assert
            Mockito.verify(mSaveTellUsAboutYouUseCase).saveUserInfo(capture(argumentCapter))
            val arguments = argumentCapter.value
            Assert.assertEquals(arguments, data)

            Mockito.verify(mFetchUserInfoUseCase, Mockito.atLeastOnce()).fetchUserInfo()
            Mockito.verify(mObserver, Mockito.atLeastOnce()).onUserInfoSaved()
        }
    }

    @Test
    fun userInfoSaved_success_failed() {
        runBlocking {
            // arange
            Mockito.`when`(mFetchUserInfoUseCase.fetchUserInfo()).thenReturn(flowOf(data))
            // act
            SUT.saveData(PROFESSION, SUMMARY).join()
            // assert
            Mockito.verify(mSaveTellUsAboutYouUseCase).saveUserInfo(capture(argumentCapter))
            val arguments = argumentCapter.value
            Assert.assertEquals(arguments, data)

            Mockito.verify(mFetchUserInfoUseCase, Mockito.atLeastOnce()).fetchUserInfo()
            Mockito.verify(mObserver, Mockito.atLeastOnce()).onUserInfoSaved()
        }
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        SUT.onStop()
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}
