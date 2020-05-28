package com.github.mdfh.flick.ui.movielist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.ItemMovieBinding
import com.github.mdfh.flick.databinding.ItemMovieCarouselBinding
import com.github.mdfh.flick.databinding.ItemMovieListBinding
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.utils.base.BaseViewHolder


class MovieListAdapter(var viewModel: MovieListViewModel) :
    ListAdapter<Movie, BaseViewHolder>(MovieListDiffCallback()) {

    var moviesList = ArrayList<Movie>()

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        val itemViewBinding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ItemViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(
        @NonNull holder: BaseViewHolder,
        position: Int
    ) {
        holder.onBind(position)
    }



    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun addItems(filesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(filesList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        moviesList.clear()
    }

    inner class ItemViewHolder(private val mBinding: ItemMovieListBinding) : BaseViewHolder(mBinding.root)
    {
        override fun onBind(position: Int) {
            mBinding.apply {
                viewmodel = viewModel
                movie = moviesList[position]
                executePendingBindings()
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class MovieListDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}