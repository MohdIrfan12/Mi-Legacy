package com.mia.legacy.ui.home.hotstar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mia.legacy.usecase.Movies.FetchHotstarMoviesUseCase
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HotstarViewModel(private val mFetchMoviesUseCase: FetchHotstarMoviesUseCase) : ViewModel() {

    private var mObserver: HotstarViewModelObserver? = null
    private val moviesFlow =
        Pager(PagingConfig(pageSize = 8)) { mFetchMoviesUseCase }.flow.cachedIn(viewModelScope)


    fun onStart(observer: HotstarViewModelObserver?) {
        this.mObserver = observer
    }

    fun onStop() {
        this.mObserver = null
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun fetchMovies() = viewModelScope.launch {
        mObserver?.showLoader()
        moviesFlow.catch {
            mObserver?.hideLoader()
            mObserver?.onMoviesFetchFailed(it.message)
        }.collect { data ->
            mObserver?.hideLoader()
            mObserver?.onMoviesFetched(data)
            mFetchMoviesUseCase.invalidate()
        }
    }
}