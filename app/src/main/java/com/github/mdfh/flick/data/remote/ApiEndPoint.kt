package com.github.mdfh.flick.data.remote

import com.github.mdfh.flick.BuildConfig

class ApiEndPoint {

    object Movies
    {
        const val ENDPOINT_POPULAR_MOVIES = BuildConfig.BASE_URL + "/3/movie/popular"
        const val ENDPOINT_UPCOMING_MOVIES = BuildConfig.BASE_URL + "/3/movie/upcoming"
        const val ENDPOINT_TOP_RATED_MOVIES = BuildConfig.BASE_URL + "/3/movie/top_rated"
    }

    object Configuration
    {
        const val ENDPOINT_CONFIGURATION = BuildConfig.BASE_URL + "/3/configuration"
    }

}