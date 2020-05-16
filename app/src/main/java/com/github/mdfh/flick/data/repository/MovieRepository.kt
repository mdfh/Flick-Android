package com.github.mdfh.flick.data.repository

import android.content.Context
import com.github.mdfh.flick.data.pref.PrefRepository
import com.github.mdfh.flick.data.remote.ApiRepository
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(private val mPreferencesHelper: PrefRepository,
            private val mApiHelper: ApiRepository)
{
    suspend fun getPopularMovies() = mApiHelper.getPopularMovies()
    suspend fun getTopRatedMovies() = mApiHelper.getTopRatedMovies()
    suspend fun getUpcomingMovies() = mApiHelper.getUpcomingMovies()
    suspend fun getNowPlayingMovies() = mApiHelper.getUpcomingMovies()
}