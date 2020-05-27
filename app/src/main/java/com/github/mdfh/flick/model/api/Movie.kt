package com.github.mdfh.flick.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

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

    @SerializedName("vote_average")
    val voteAverage : Float,

    @SerializedName("budget")
    val budget : Long,

    @SerializedName("imdb_id")
    val imdbId : String,

    @SerializedName("release_date")
    val releaseDate : Date,

    @SerializedName("revenue")
    val revenue : Long


) : Serializable

/*
{
  "adult": false,
  "backdrop_path": "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
  "belongs_to_collection": null,
  "budget": 87500000,
  "genres": [
    {
      "id": 878,
      "name": "Science Fiction"
    },
    {
      "id": 18,
      "name": "Drama"
    }
  ],
  "homepage": "https://www.foxmovies.com/movies/ad-astra",
  "id": 419704,
  "imdb_id": "tt2935510",
  "original_language": "en",
  "original_title": "Ad Astra",
  "overview": "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
  "popularity": 680.873,
  "poster_path": "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
  "production_companies": [
    {
      "id": 490,
      "logo_path": null,
      "name": "New Regency Productions",
      "origin_country": ""
    },
    {
      "id": 79963,
      "logo_path": null,
      "name": "Keep Your Head",
      "origin_country": ""
    },
    {
      "id": 73492,
      "logo_path": null,
      "name": "MadRiver Pictures",
      "origin_country": ""
    },
    {
      "id": 81,
      "logo_path": "/8wOfUhA7vwU2gbPjQy7Vv3EiF0o.png",
      "name": "Plan B Entertainment",
      "origin_country": "US"
    },
    {
      "id": 30666,
      "logo_path": "/g8LmDZVFWDRJW72sj0nAj1gh8ac.png",
      "name": "RT Features",
      "origin_country": "BR"
    },
    {
      "id": 30148,
      "logo_path": "/zerhOenUD6CkH8SMgZUhrDkOs4w.png",
      "name": "Bona Film Group",
      "origin_country": "CN"
    },
    {
      "id": 22213,
      "logo_path": "/qx9K6bFWJupwde0xQDwOvXkOaL8.png",
      "name": "TSG Entertainment",
      "origin_country": "US"
    }
  ],
  "production_countries": [
    {
      "iso_3166_1": "BR",
      "name": "Brazil"
    },
    {
      "iso_3166_1": "CN",
      "name": "China"
    },
    {
      "iso_3166_1": "US",
      "name": "United States of America"
    }
  ],
  "release_date": "2019-09-17",
  "revenue": 132807427,
  "runtime": 123,
  "spoken_languages": [
    {
      "iso_639_1": "en",
      "name": "English"
    },
    {
      "iso_639_1": "no",
      "name": "Norsk"
    }
  ],
  "status": "Released",
  "tagline": "The answers we seek are just outside our reach",
  "title": "Ad Astra",
  "video": false,
  "vote_average": 6,
  "vote_count": 3500
}
 */