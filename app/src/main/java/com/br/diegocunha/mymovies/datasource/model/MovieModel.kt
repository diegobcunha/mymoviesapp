package com.br.diegocunha.mymovies.datasource.model

import com.squareup.moshi.Json

data class GenreResponse(val genres: List<Genre>)

data class Genre(val id: Int, val name: String)

data class UpcomingMoviesResponse(
    override var page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") override val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
): Page

data class Movie(
    val id: Long,
    val title: String,
    val overview: String?,
    val genres: List<Genre>?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String
)