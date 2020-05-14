package com.github.mdfh.flick.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.github.mdfh.flick.data.Result as Result;

class HomeViewModel  @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    init {
        getPopularMovies()
    }

    val post: MutableLiveData<MovieList> by lazy {
        MutableLiveData<MovieList>()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val retrofitPost = repository.getPopularMovies()
            when (retrofitPost) {
                is Result.Success -> { post.postValue(retrofitPost.data)}
                is Result.Error -> {
                    Log.d("Error", "Error")
                }
            }
        }

    }
}
