/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.github.mdfh.flick.data.remote

import android.content.Context
import com.github.mdfh.flick.data.DataResult
import com.github.mdfh.flick.data.remote.services.ConfigurationService
import com.github.mdfh.flick.data.remote.services.MovieService
import com.github.mdfh.flick.model.api.Configuration
import com.github.mdfh.flick.model.api.MovieList
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Faraz on 07/07/17.
 */

interface ApiRepository {
   suspend fun getPopularMovies(page : Int = 1): DataResult<MovieList>
   suspend fun getUpcomingMovies(page : Int = 1): DataResult<MovieList>
   suspend fun getTopRatedMovies(page : Int = 1): DataResult<MovieList>
   suspend fun getNowPlayingMovies(): DataResult<MovieList>

    suspend fun getConfiguration() : DataResult<Configuration>
}

@Singleton
class AppApiRepository @Inject
constructor(
    private val context : Context,
    private val movieService: MovieService,
    private val configurationService: ConfigurationService,
    private val gson: Gson
) : ApiRepository
{
    override suspend fun getPopularMovies(page : Int): DataResult<MovieList> {
        return safeApiCall(call = { movieService.getPopularMovies(page) });
    }

    override suspend fun getUpcomingMovies(page : Int): DataResult<MovieList> {
        return safeApiCall(call = { movieService.getUpcomingMovies(page) });
    }

    override suspend fun getTopRatedMovies(page : Int): DataResult<MovieList> {
        return safeApiCall(call = { movieService.getTopRatedMovies(page) });
    }

    override suspend fun getNowPlayingMovies(): DataResult<MovieList> {
        return safeApiCall ( call = { movieService.getNowPlayingMovies() });
    }

    override suspend fun getConfiguration(): DataResult<Configuration> {
        return safeApiCall ( call = { configurationService.getConfiguration() } );
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): DataResult<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                DataResult.Success(myResp.body()!!)
            } else {

                /*
                handle standard error codes
                if (myResp.code() == 403){
                    Log.i("responseCode","Authentication failed")
                }
                .
                .
                .
                 */

                DataResult.Error(Exception(myResp.errorBody()?.string() ?: "Something goes wrong"))
            }

        } catch (e: Exception) {
            DataResult.Error(Exception(e.message ?: "Internet error runs"))
        }
    }


}