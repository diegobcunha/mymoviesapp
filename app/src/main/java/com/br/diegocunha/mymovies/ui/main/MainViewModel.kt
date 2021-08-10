package com.br.diegocunha.mymovies.ui.main

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.api.rest.TmdbExecutor
import com.br.diegocunha.mymovies.datasource.model.GenreResponse
import com.br.diegocunha.mymovies.datasource.model.UpcomingMoviesResponse
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.templates.viewmodel.PaginableViewModel
import com.br.diegocunha.mymovies.ui.templates.viewmodel.SuspendFetchViewModel

class MainViewModel(dispatchersProvider: DispatchersProvider, private val executor: TmdbExecutor) :
    PaginableViewModel<UpcomingMoviesResponse>(dispatchersProvider) {

    override suspend fun fetch(page: Int): Resource<UpcomingMoviesResponse> {
        return executor.upComingMovies(page)
    }
}