package com.github.mdfh.flick.data.remote.services

import com.github.mdfh.flick.data.remote.ApiEndPoint
import com.github.mdfh.flick.model.api.Configuration
import com.github.mdfh.flick.model.api.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TokenService
{
}

interface MovieService {
    @GET(ApiEndPoint.Movies.ENDPOINT_POPULAR_MOVIES)
    suspend  fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_UPCOMING_MOVIES)
    suspend  fun getUpcomingMovies(
        @Query("page") page: Int
    ): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_TOP_RATED_MOVIES)
    suspend  fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_NOW_PLAYING_MOVIES)
    suspend  fun getNowPlayingMovies(): Response<MovieList>
}

interface ConfigurationService {
    @GET(ApiEndPoint.Configuration.ENDPOINT_CONFIGURATION)
    suspend  fun getConfiguration(): Response<Configuration>
}