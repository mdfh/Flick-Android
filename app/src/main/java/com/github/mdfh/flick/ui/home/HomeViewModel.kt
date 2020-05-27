package com.github.mdfh.flick.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mdfh.flick.Event
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.model.api.MovieList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> = _openMovieEvent

    init {
        init()
    }

    val popularMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val topRatedMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val upcomingMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val nowPlaying: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    /**
     * Called by Data Binding.
     */
    fun openMovie(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }


    private fun init() {
        // Collect the flow

        movieRepository.getMoviesList()
            .map { value ->
                when(value.first)
                {
                    MovieType.NOW_PLAYING -> nowPlaying.value = value.second.results
                    MovieType.POPULAR -> popularMovies.value = value.second.results
                    MovieType.TOP_RATED -> topRatedMovies.value = value.second.results
                    MovieType.UPCOMING -> upcomingMovies.value = value.second.results
                }
            }
            .catch { throwable ->  Log.e("Error", "Error" , throwable)  }
            .launchIn(viewModelScope)

    }
}
