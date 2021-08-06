package com.br.diegocunha.mymovies.ui.movie

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.api.rest.TmdbExecutor
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.templates.viewmodel.SuspendFetchViewModel

class MovieDetailViewModel(
    dispatchersProvider: DispatchersProvider,
    private val id: Long,
    private val executor: TmdbExecutor
) : SuspendFetchViewModel<Movie>(dispatchersProvider) {

    override suspend fun fetch(loadingType: LoadingType): Resource<Movie> {
        return executor.movieDetail(id)
    }
}