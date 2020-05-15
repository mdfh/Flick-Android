package com.github.mdfh.flick.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mdfh.flick.data.Result
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.model.api.MovieList
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

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

    private fun init() {
        viewModelScope.launch {
            val popularResult = movieRepository.getPopularMovies()
            val topRatedResult = movieRepository.getTopRatedMovies()
            val upcomingResult = movieRepository.getUpcomingMovies()

            when (popularResult) {
                is Result.Success -> { popularMovies.postValue(popularResult.data.results)}
                is Result.Error -> {
                    Log.d("Error", "Error")
                }
            }

            when (topRatedResult) {
                is Result.Success -> { topRatedMovies.postValue(topRatedResult.data.results)}
                is Result.Error -> {
                    Log.d("Error", "Error")
                }
            }

            when (upcomingResult) {
                is Result.Success -> { upcomingMovies.postValue(upcomingResult.data.results)}
                is Result.Error -> {
                    Log.d("Error", "Error")
                }
            }
        }

    }
}
