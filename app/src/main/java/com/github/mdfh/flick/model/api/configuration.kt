package com.github.mdfh.flick.model.api

import com.google.gson.annotations.SerializedName

data class Configuration
    (
    @SerializedName("images")
    val imageConfiguration: ImageConfiguration

)

data class ImageConfiguration
    (
    @SerializedName("base_url")
    val originalTitle: String,

    @SerializedName("secure_base_url")
    val secureBaseUrl: String,

    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>,

    @SerializedName("logo_sizes")
    val logoSizes: List<String>,

    @SerializedName("poster_sizes")
    val posterSizes: List<String>,

    @SerializedName("profile_sizes")
    val profileSizes: List<String>,

    @SerializedName("still_sizes")
    val stillSizes: List<String>
)