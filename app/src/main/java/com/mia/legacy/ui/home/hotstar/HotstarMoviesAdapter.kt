package com.mia.legacy.ui.home.hotstar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mia.legacy.databinding.ItemViewHotstarMovieBinding
import com.mia.legacy.ui.common.loadImage
import com.mia.legacy.usecase.Movies.Movie

/**
 * Created by Mohd Irfan on 15/8/21.
 */
class HotstarMoviesAdapter(
    diffCallback: DiffUtil.ItemCallback<Movie>,
    private val baseUrl: String?
) :
    PagingDataAdapter<Movie, HotstarMoviesAdapter.MoviesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewHotstarMovieBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.tvTitle.setText("Movie Name : - "+movie?.title)
        holder.binding.ivCoverPic.loadImage(baseUrl + movie?.poster_path)
    }

    inner class MoviesViewHolder(val binding: ItemViewHotstarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}