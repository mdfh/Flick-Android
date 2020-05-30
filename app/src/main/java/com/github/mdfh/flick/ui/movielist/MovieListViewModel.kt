package com.github.mdfh.flick.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.mdfh.flick.Event
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.ui.home.MovieType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieListViewModel  @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private lateinit var factory: MovieSourceFactory
    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> = _openMovieEvent

    var movies  :LiveData<PagedList<Movie>>

    init {
        movies  = initializedPagedListBuilder().build()
    }

    /**
     * Called by Data Binding.
     */
    fun openMovie(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }

    private fun initializedPagedListBuilder():
            LivePagedListBuilder<Int, Movie> {

        factory = MovieSourceFactory(viewModelScope, movieRepository)
        return LivePagedListBuilder(factory, MovieSourceFactory.providePagingConfig())
    }

    fun start(movieType: MovieType)
    {
        factory.setMovieType(movieType)
        movies.value?.dataSource?.invalidate()
    }

   /* private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()*/

}

