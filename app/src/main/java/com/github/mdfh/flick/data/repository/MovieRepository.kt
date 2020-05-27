package com.github.mdfh.flick.data.repository

import android.content.Context
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.pref.PrefRepository
import com.github.mdfh.flick.data.remote.ApiRepository
import com.github.mdfh.flick.model.api.MovieList
import com.github.mdfh.flick.ui.home.MovieType
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(private val mPreferencesHelper: PrefRepository,
            private val mApiHelper: ApiRepository)
{
    fun getMoviesList() = flow<Pair<MovieType, MovieList>> {

        when (val nowPlaying = mApiHelper.getNowPlayingMovies()) {
            is DataResult.Success -> { emit(Pair(MovieType.NOW_PLAYING, nowPlaying.data)) }
            is DataResult.Error -> {

            }
        }

        when (val popularMovies = mApiHelper.getPopularMovies()) {
            is DataResult.Success -> { emit(Pair(MovieType.POPULAR, popularMovies.data)) }
            is DataResult.Error -> {

            }
        }

        when (val topRatedMovies = mApiHelper.getTopRatedMovies()) {
            is DataResult.Success -> { emit(Pair(MovieType.TOP_RATED, topRatedMovies.data)) }
            is DataResult.Error -> {

            }
        }

        when (val upComingMovies = mApiHelper.getUpcomingMovies()) {
            is DataResult.Success -> { emit(Pair(MovieType.UPCOMING, upComingMovies.data)) }
            is DataResult.Error -> {

            }
        }
    }.flowOn(Dispatchers.IO)
}