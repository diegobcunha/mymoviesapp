package com.br.diegocunha.mymovies.datasource.api.rest

import com.br.diegocunha.mymovies.BuildConfig
import com.br.diegocunha.mymovies.datasource.model.GenreResponse
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.datasource.model.UpcomingMoviesResponse
import com.br.diegocunha.mymovies.datasource.resource.Resource

class TmdbExecutor(private val api: TmdbApi) {

    suspend fun gentMoviesGenre(page: Int): Resource<GenreResponse> {
        return api.genres(BuildConfig.API_KEY, BuildConfig.DEFAULT_LANGUAGE, page)
    }

    suspend fun upComingMovies(page: Long): Resource<UpcomingMoviesResponse> {
        return api.upcomingMovies(BuildConfig.API_KEY, BuildConfig.DEFAULT_LANGUAGE, page, "BR")
            .map {
                val original = it?.results
                val copyResults = original.orEmpty().toMutableList()
                copyResults.forEach { movie ->
                    movie.posterPath = POSTER_URL + movie.posterPath
                }

                it?.copy(results = copyResults)
            }
    }

    suspend fun movieDetail(id: Long): Resource<Movie> {
        return api.movie(id, BuildConfig.API_KEY, BuildConfig.DEFAULT_LANGUAGE)
            .map { it?.copy(posterPath = POSTER_URL + it.posterPath ) }
    }

    companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w500"
    }
}