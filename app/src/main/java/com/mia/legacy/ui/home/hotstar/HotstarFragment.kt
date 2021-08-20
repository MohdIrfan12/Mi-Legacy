package com.mia.legacy.ui.home.hotstar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import com.mia.legacy.MiApplication
import com.mia.legacy.databinding.HotstarFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment
import com.mia.legacy.usecase.Movies.Movie

class HotstarFragment : BaseFragment(), HotstarViewModelObserver {

    private lateinit var pagingAdapter: HotstarMoviesAdapter
    private lateinit var binding: HotstarFragmentBinding
    private lateinit var viewModel: HotstarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, factory).get(HotstarViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
        binding = HotstarFragmentBinding.inflate(inflater, container, false)
        setMoviesAdapter()
        return binding.root
    }

    private fun setMoviesAdapter() {
        val path = (requireActivity().application as MiApplication).getImageBasePath()
         pagingAdapter = HotstarMoviesAdapter(HostStarMoviesAdapterPageComparator(), path)
        binding.rvHotstar.adapter = pagingAdapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart(this)
        viewModel.fetchMovies()
    }


    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding.progressBar.visibility = View.GONE
    }

    override suspend fun onMoviesFetched(movies: PagingData<Movie>) {
        pagingAdapter.submitData(movies)
    }

    override fun onMoviesFetchFailed(message: String?) {
        mPresentationCompositionRoot.getToastHelper().showToast(message)
    }
}