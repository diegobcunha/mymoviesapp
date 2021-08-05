package com.br.diegocunha.mymovies.datasource.api.rest

import com.br.diegocunha.mymovies.BuildConfig
import com.br.diegocunha.mymovies.datasource.model.GenreResponse
import com.br.diegocunha.mymovies.datasource.resource.Resource

class TmdbExecutor(private val api: TmdbApi) {

    suspend fun gentMoviesGenre(page: Int): Resource<GenreResponse> {
        return api.genres(BuildConfig.API_KEY, BuildConfig.DEFAULT_LANGUAGE, page)
    }
}