package com.mia.legacy.ui.home.hotstar

import com.mia.legacy.usecase.Movies.FetchHotstarMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohd Irfan on 18/8/21.
 */
@RunWith(MockitoJUnitRunner::class)
internal class HotstarViewModelTest {

    @Mock
    private lateinit var mObserver: HotstarViewModelObserver

    @Mock
    private lateinit var mFetchHotstarMoviesUseCase: FetchHotstarMoviesUseCase
    private lateinit var SUT: HotstarViewModel
    private val testScope = newSingleThreadContext("Test")

    @Before
    fun before() {
        Dispatchers.setMain(testScope)
        SUT = HotstarViewModel(mFetchHotstarMoviesUseCase)
        SUT.onStart(mObserver)
    }

    @Test
    fun fetchMovies_sucees_notifyObserbers() {

    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }
}