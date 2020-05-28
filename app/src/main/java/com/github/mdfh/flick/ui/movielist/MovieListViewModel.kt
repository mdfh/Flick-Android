package com.github.mdfh.flick.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mdfh.flick.Event
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.ui.home.MovieType
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel  @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> = _openMovieEvent

    val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    /**
     * Called by Data Binding.
     */
    fun openMovie(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }


    fun start(movieType: MovieType) {
        viewModelScope.launch {
            var result = when(movieType)
            {
                MovieType.POPULAR -> movieRepository.getPopularMovies()
                MovieType.TOP_RATED -> movieRepository.getTopRatedMovies()
                MovieType.UPCOMING -> movieRepository.getUpcomingMovies()
                else -> null
            }
            when(result)
            {
                is DataResult.Success -> {
                    movies.value = result.data.results
                }
                is DataResult.Error -> {

                }
            }
        }
    }
}
