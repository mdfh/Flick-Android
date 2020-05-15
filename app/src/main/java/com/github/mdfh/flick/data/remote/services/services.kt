package com.github.mdfh.flick.data.remote.services

import com.github.mdfh.flick.data.remote.ApiEndPoint
import com.github.mdfh.flick.model.api.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface TokenService
{
}

interface MovieService {
    @GET(ApiEndPoint.ENDPOINT_POPULAR_MOVIES)
    suspend  fun getPopularMovies(): Response<MovieList>

    @GET(ApiEndPoint.ENDPOINT_UPCOMING_MOVIES)
    suspend  fun getUpcomingMovies(): Response<MovieList>

    @GET(ApiEndPoint.ENDPOINT_TOP_RATED_MOVIES)
    suspend  fun getTopRatedMovies(): Response<MovieList>
}