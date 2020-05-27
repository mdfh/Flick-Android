package com.github.mdfh.flick.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mdfh.flick.Event
import com.github.mdfh.flick.data.repository.ConfigurationRepository
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieInitializedCommand = MutableLiveData<Event<Movie>>()
    val movieInitializedCommand: LiveData<Event<Movie>> = _movieInitializedCommand

    fun start(movie: Movie) {
        _movieInitializedCommand.value = Event(movie)

        fetchMovie(movie.id)
    }

    private fun fetchMovie(id: Long) {

    }
}
