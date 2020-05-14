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
import com.github.mdfh.flick.model.api.Movie


class HomeAdapter(var context: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var moviesList = ArrayList<Movie>()

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(
        @NonNull holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val movie: Movie = moviesList[position]
        val viewHolder =
            holder as RecyclerViewViewHolder
        viewHolder.txtView_title.setText(movie.title)
        viewHolder.txtView_description.setText(movie.overview)
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

    internal inner class RecyclerViewViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgView_icon: ImageView
        var txtView_title: TextView
        var txtView_description: TextView

        init {
            imgView_icon = itemView.findViewById(R.id.imgView_icon)
            txtView_title = itemView.findViewById(R.id.txtView_title)
            txtView_description = itemView.findViewById(R.id.txtView_description)
        }
    }
}