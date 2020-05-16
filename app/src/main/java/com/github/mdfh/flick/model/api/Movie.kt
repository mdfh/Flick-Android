package com.github.mdfh.flick.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieList
(
    @SerializedName("page")
    val page : Int,

    @SerializedName("total_results")
    val totalResults : Int,

    @SerializedName("total_pages")
    val totalPages : Int,

    @SerializedName("results")
    val results : List<Movie>
)

data class Movie
    (
    @SerializedName("popularity")
    val popularity : Float,

    @SerializedName("vote_count")
    val voteCount : Long,

    @SerializedName("video")
    val video : Boolean,

    @SerializedName("poster_path")
    val posterPath : String,

    @SerializedName("id")
    val id : Long,

    @SerializedName("adult")
    val adult : Boolean,

    @SerializedName("backdrop_path")
    val backdropPath : String,

    @SerializedName("original_language")
    val originalLanguage : String,

    @SerializedName("original_title")
    val originalTitle : String,

    @SerializedName("title")
    val title : String,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("genre_ids")
    val genreIds : List<Int>,

    @SerializedName("vote_average")
    val voteAverage : Float,

    @SerializedName("release_date")
    val releaseDate : String
)