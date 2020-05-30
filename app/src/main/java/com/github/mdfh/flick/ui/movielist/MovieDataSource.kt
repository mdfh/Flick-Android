package com.github.mdfh.flick.ui.movielist

import android.util.Log
import androidx.fragment.app.testing.FragmentScenario.launch
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.remote.NetworkState
import com.github.mdfh.flick.data.repository.MovieRepository
import com.github.mdfh.flick.model.api.Movie
import com.github.mdfh.flick.model.api.MovieList
import com.github.mdfh.flick.ui.home.MovieType
import kotlinx.coroutines.*
import java.util.*

class MovieSourceFactory(
    private val scope: CoroutineScope,
    private val repository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    private var movieType : MovieType? = null
    val mutableLiveData = MutableLiveData<MovieDataSource>()

    companion object {
        fun providePagingConfig(): PagedList.Config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
    }

    override fun create(): DataSource<Int, Movie> {
        val source = MovieDataSource(scope, repository, movieType)
        mutableLiveData.postValue(source)
        return source
    }

    fun setMovieType(movieType: MovieType)
    {
        this.movieType = movieType
    }
}

class MovieDataSource(
    private val scope: CoroutineScope,
    private val repository: MovieRepository,
    private val movieType: MovieType?
) :
    PageKeyedDataSource<Int, Movie>() {

    // FOR DATA ---
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob) //coroutine scope

    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? =
        null // Keep reference of the last query (to be able to retry it if necessary)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        scope.launch {
            if(movieType == null)
                return@launch

            val result = getMovieList(1)
            when (result) {
                is DataResult.Success -> {
                    callback.onResult(
                        result.data.results,
                        null,
                        2
                    )
                }
                is DataResult.Error -> {
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            when (val result = getMovieList(params.key)) {
                is DataResult.Success -> {
                    val nextPage = if(result.data.page == result.data.totalPages) null else result.data.page + 1
                    callback.onResult(result.data.results, nextPage)
                }
                is DataResult.Error -> {
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        completableJob.cancel()
    }

    private suspend fun getMovieList(page: Int): DataResult<MovieList> {
        return when (movieType) {
            MovieType.POPULAR -> repository.getPopularMovies(page)
            MovieType.TOP_RATED -> repository.getTopRatedMovies(page)
            MovieType.UPCOMING -> repository.getUpcomingMovies(page)
            else -> repository.getPopularMovies(page)
        }
    }

}
