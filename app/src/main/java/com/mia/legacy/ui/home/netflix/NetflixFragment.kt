package com.mia.legacy.ui.home.netflix

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mia.legacy.MiApplication
import com.mia.legacy.databinding.NetflixFragmentBinding
import com.mia.legacy.ui.common.binders.BaseFragment
import com.mia.legacy.usecase.Movies.Movie

class NetflixFragment : BaseFragment(), NetflixViewModelObserver {


    private lateinit var binding: NetflixFragmentBinding
    private lateinit var viewModel: NetflixViewModel
    private lateinit var mNetflixMoviesAdapter: NetflixMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, factory).get(NetflixViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NetflixFragmentBinding.inflate(inflater, container, false)
        setMoviesAdapter()
        return binding.root
    }

    private fun setMoviesAdapter() {
        val path = (requireActivity().application as MiApplication).getImageBasePath()
        mNetflixMoviesAdapter = NetflixMoviesAdapter(path)
        binding.rvNetflix.adapter = mNetflixMoviesAdapter

    }

    override fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding.progressBar.visibility = View.GONE
    }

    override suspend fun onMoviesFetched(movies: List<Movie>) {
        mNetflixMoviesAdapter.setData(movies)
    }

    override fun onMoviesFetchFailed(message: String?) {
        mPresentationCompositionRoot.getToastHelper().showToast(message)
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

}