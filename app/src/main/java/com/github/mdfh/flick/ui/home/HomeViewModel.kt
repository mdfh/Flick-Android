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
    private val repository: MovieRepository
) : ViewModel() {

    init {
        getPopularMovies()
    }

    val popularMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            val retrofitPost = repository.getPopularMovies()
            when (retrofitPost) {
                is Result.Success -> { popularMovies.postValue(retrofitPost.data.results)}
                is Result.Error -> {
                    Log.d("Error", "Error")
                }
            }
        }

    }
}
