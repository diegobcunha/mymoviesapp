package com.br.diegocunha.mymovies.ui.templates.viewmodel

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.datasource.resource.Resource.Companion.error
import com.br.diegocunha.mymovies.datasource.resource.Resource.Companion.loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class SuspendFetchViewModel<I>(dispatchersProvider: DispatchersProvider) :
    FetchViewModel<I>(dispatchersProvider) {

    override fun loadContent(loadingType: LoadingType): Flow<Resource<I>> {
        return createFlowData(loadingType)
    }

    private fun createFlowData(loadingType: LoadingType) = flow<Resource<I>> {
        emit(loading(loadingType))
        tryFetch(loadingType)
    }

    private suspend fun FlowCollector<Resource<I>>.tryFetch(loadingType: LoadingType) {
        try {
            val fetchResource = withContextFetch(loadingType)
            when {
                !fetchResource.isSuccess() -> {
                    emit(error(fetchResource.getThrowableOrNull()))
                }

                fetchResource.isNotNullSuccess() -> {
                    emit(fetchResource)
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }


    private suspend fun withContextFetch(loadingType: LoadingType) = withContext(fetchContext()) {
        fetch(loadingType).onSuccess {
            lastRequestedValue = it
        }
    }


    protected abstract suspend fun fetch(loadingType: LoadingType): Resource<I>
}