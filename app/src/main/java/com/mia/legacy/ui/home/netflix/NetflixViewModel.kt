package com.mia.legacy.ui.home.netflix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mia.legacy.usecase.Movies.FetchNetflixMoviesUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class NetflixViewModel(private val mUseCase: FetchNetflixMoviesUseCase) : ViewModel() {

    private var mObserver: NetflixViewModelObserver? = null

    fun onStart(observer: NetflixViewModelObserver?) {
        this.mObserver = observer
    }

    fun onStop() {
        this.mObserver = null
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            mUseCase.fetchMovies(viewModelScope)
                .catch {
                    mObserver?.hideLoader()
                    mObserver?.onMoviesFetchFailed(it.message)
                }
                .collect {
                    mObserver?.hideLoader()
                    mObserver?.onMoviesFetched(it)
                }
        }
    }

}