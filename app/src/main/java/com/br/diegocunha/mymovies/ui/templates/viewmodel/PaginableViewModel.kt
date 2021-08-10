package com.br.diegocunha.mymovies.ui.templates.viewmodel

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.model.NextPageListener
import com.br.diegocunha.mymovies.datasource.model.Page
import com.br.diegocunha.mymovies.datasource.model.increment
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource

abstract class PaginableViewModel<I : Page>(dispatchersProvider: DispatchersProvider) :
    SuspendFetchViewModel<I>(dispatchersProvider), NextPageListener {

    protected var currentPage: Int = resourceStateFlow.value.data?.page ?: 1

    final override suspend fun fetch(loadingType: LoadingType): Resource<I> {
        val page = if (loadingType == LoadingType.PAGINATION) {
            currentPage.increment()
        } else {
            currentPage
        }

        return fetch(page).onSuccess {
            currentPage = page
        }
    }

    abstract suspend fun fetch(page: Int): Resource<I>


    override fun loadNextPage() {
        if (hasMoreData() && !resourceStateFlow.value.isLoading()) {
            forceLoad(LoadingType.PAGINATION)
        }
    }

    private fun hasMoreData() = currentPage < resourceStateFlow.value.data?.totalPages ?: 0
}