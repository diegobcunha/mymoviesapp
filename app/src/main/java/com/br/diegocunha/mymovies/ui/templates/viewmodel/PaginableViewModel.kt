package com.br.diegocunha.mymovies.ui.templates.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.model.NextPageListener
import com.br.diegocunha.mymovies.datasource.model.Page
import com.br.diegocunha.mymovies.datasource.model.increment
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource

abstract class PaginableViewModel<O, I : Page<O>>(dispatchersProvider: DispatchersProvider) :
    SuspendFetchViewModel<I>(dispatchersProvider), NextPageListener {

    val currentPage by lazy {
        mutableStateOf(resourceStateFlow.value.data?.page ?: 1)
    }

    val items = mutableStateListOf<O>()

    final override suspend fun fetch(loadingType: LoadingType): Resource<I> {
        val page = if (loadingType == LoadingType.PAGINATION) {
            currentPage.increment()
        } else {
            currentPage.value
        }

        return fetch(page).onSuccess {
            currentPage.value = page
            appendItems(it?.results.orEmpty())
        }
    }

    abstract suspend fun fetch(page: Int): Resource<I>

    override fun loadNextPage() {
        if (hasMoreData() && !resourceStateFlow.value.isLoading()) {
            forceLoad(LoadingType.PAGINATION)
        }
    }

    private fun hasMoreData() = currentPage.value < resourceStateFlow.value.data?.totalPages ?: 0

    private fun appendItems(items: List<O>) {
        this.items.addAll(items)
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}