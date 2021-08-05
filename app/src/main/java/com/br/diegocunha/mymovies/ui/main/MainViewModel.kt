package com.br.diegocunha.mymovies.ui.main

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.api.rest.TmdbExecutor
import com.br.diegocunha.mymovies.datasource.model.GenreResponse
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.templates.viewmodel.SuspendFetchViewModel

class MainViewModel(dispatchersProvider: DispatchersProvider, private val executor: TmdbExecutor) :
    SuspendFetchViewModel<GenreResponse>(dispatchersProvider) {

    override suspend fun fetch(loadingType: LoadingType): Resource<GenreResponse> {
        return executor.gentMoviesGenre(1)
    }
}