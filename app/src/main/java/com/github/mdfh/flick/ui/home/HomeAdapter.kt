package com.github.mdfh.flick.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.ItemMovieBinding
import com.github.mdfh.flick.databinding.ItemMovieCarouselBinding
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.utils.base.BaseViewHolder


class HomeAdapter(var context: Activity, var isCarousel : Boolean = false) :
    RecyclerView.Adapter<BaseViewHolder>() {
    var moviesList = ArrayList<Movie>()

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        if(isCarousel)
        {
            val itemViewBinding = ItemMovieCarouselBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)

            return CarouselItemViewHolder(itemViewBinding)
        }
        else
        {
            val itemViewBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)

            return ItemViewHolder(itemViewBinding)
        }

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

    inner class ItemViewHolder(private val mBinding: ItemMovieBinding) : BaseViewHolder(mBinding.root)
    {
        override fun onBind(position: Int) {
            mBinding.imageUrl = "https://image.tmdb.org/t/p/w300${moviesList[position].posterPath}"
            mBinding.executePendingBindings();
        }
    }

    inner class CarouselItemViewHolder(private val mBinding: ItemMovieCarouselBinding) : BaseViewHolder(mBinding.root)
    {
        override fun onBind(position: Int) {
            mBinding.imageUrl = "https://image.tmdb.org/t/p/w780${moviesList[position].backdropPath}"
            mBinding.executePendingBindings();
        }
    }
}