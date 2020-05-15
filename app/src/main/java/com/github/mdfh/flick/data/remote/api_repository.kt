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
import com.github.mdfh.flick.data.Result
import com.github.mdfh.flick.data.remote.services.MovieService
import com.github.mdfh.flick.model.api.MovieList
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Faraz on 07/07/17.
 */

interface ApiRepository {
   suspend fun getPopularMovies(): Result<MovieList>
   suspend fun getUpcomingMovies(): Result<MovieList>
   suspend fun getTopRatedMovies(): Result<MovieList>
}

@Singleton
class AppApiRepository @Inject
constructor(
    private val context : Context,
    private val usersService: MovieService,
    private val gson: Gson
) : ApiRepository
{
    override suspend fun getPopularMovies(): Result<MovieList> {
        return safeApiCall(call = { usersService.getPopularMovies() });
    }

    override suspend fun getUpcomingMovies(): Result<MovieList> {
        return safeApiCall(call = { usersService.getUpcomingMovies() });
    }

    override suspend fun getTopRatedMovies(): Result<MovieList> {
        return safeApiCall(call = { usersService.getTopRatedMovies() });
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
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

                Result.Error(Exception(myResp.errorBody()?.string() ?: "Something goes wrong"))
            }

        } catch (e: Exception) {
            Result.Error(Exception(e.message ?: "Internet error runs"))
        }
    }


}