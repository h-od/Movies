package com.hod.themoviedb.list

import com.google.gson.annotations.SerializedName

data class List(
        @SerializedName("page") val page: Int? = null,
        @SerializedName("total_results") val totalResults: Int? = null,
        @SerializedName("total_pages") val totalPages: Int? = null,
        @SerializedName("results") val results: kotlin.collections.List<Movie>? = null)

data class Movie(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("vote_count") val voteCount: Int? = null,
        @SerializedName("video") val video: Boolean? = null,
        @SerializedName("vote_average") val voteAverage: Double? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("release_date") val releaseDate: String? = null,
        @SerializedName("popularity") val popularity: Double? = null,
        @SerializedName("poster_path") val posterPath: String? = null,
        @SerializedName("original_language") val originalLanguage: String? = null,
        @SerializedName("original_title") val originalTitle: String? = null,
        @SerializedName("genre_ids") val genreIds: kotlin.collections.List<Int>? = null,
        @SerializedName("backdrop_path") val backdropPath: Any? = null,
        @SerializedName("adult") val adult: Boolean? = null,
        @SerializedName("overview") val overview: String? = null)