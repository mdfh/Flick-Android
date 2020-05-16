package com.github.mdfh.flick.data.remote.services

import com.github.mdfh.flick.data.remote.ApiEndPoint
import com.github.mdfh.flick.model.api.Configuration
import com.github.mdfh.flick.model.api.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface TokenService
{
}

interface MovieService {
    @GET(ApiEndPoint.Movies.ENDPOINT_POPULAR_MOVIES)
    suspend  fun getPopularMovies(): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_UPCOMING_MOVIES)
    suspend  fun getUpcomingMovies(): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_TOP_RATED_MOVIES)
    suspend  fun getTopRatedMovies(): Response<MovieList>

    @GET(ApiEndPoint.Movies.ENDPOINT_NOW_PLAYING_MOVIES)
    suspend  fun getNowPlayingMovies(): Response<MovieList>
}

interface ConfigurationService {
    @GET(ApiEndPoint.Configuration.ENDPOINT_CONFIGURATION)
    suspend  fun getConfiguration(): Response<Configuration>
}