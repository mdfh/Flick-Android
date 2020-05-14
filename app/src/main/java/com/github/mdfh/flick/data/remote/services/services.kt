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
}