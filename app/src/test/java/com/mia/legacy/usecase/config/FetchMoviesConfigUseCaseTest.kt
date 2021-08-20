package com.mia.legacy.usecase.config

import com.google.gson.Gson
import com.mia.legacy.networking.FetchMoviesConfigEndpoint
import com.mia.legacy.networking.core.NetworkResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onErrorCollect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

/**
 * Created by Mohd Irfan on 18/8/21.
 */
@RunWith(MockitoJUnitRunner::class)
internal class FetchMoviesConfigUseCaseTest {

    private  var exception: RuntimeException? = null
    private var mMovieConfig: MovieConfig? = null

    @Mock
    private lateinit var mFetchMoviesConfigEndpoint: FetchMoviesConfigEndpoint
    private val gson = Gson()
    private lateinit var SUT: FetchMoviesConfigUseCase

    @Before
    fun setup() {
        SUT = FetchMoviesConfigUseCase(mFetchMoviesConfigEndpoint, gson)
    }

    @Test
    fun fetcUserData_success_notifyObserber() {
        // arrange
        success()
        // act
        val result = SUT.getConfig()
        // Assert
        runBlocking {
            result.collect {
                Assert.assertEquals(it, mMovieConfig)
            }
        }
    }

    @Test
    fun fetcUserData_failure_notifyObserber() {
        // arrange
        // act
        // Assert
        runBlocking {
            val flow = SUT.getConfig();
            flow.catch { throwable ->
                Assert.assertEquals(throwable, exception)
            }
        }
    }


    private fun success() {
        val images = Images("hello", arrayListOf<String>())
        mMovieConfig = MovieConfig(images)
        val string = gson.toJson(mMovieConfig)
        val successResponse = NetworkResponse.Success(string)
        Mockito.`when`(mFetchMoviesConfigEndpoint.fetchConfig()).thenReturn(flowOf(successResponse))
    }
}
