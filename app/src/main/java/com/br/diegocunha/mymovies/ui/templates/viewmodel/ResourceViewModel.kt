package com.br.diegocunha.mymovies.ui.templates.viewmodel

import androidx.lifecycle.viewModelScope
import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

abstract class ResourceViewModel<T>(dispatchersProvider: DispatchersProvider) :
    CoroutineViewModel(dispatchersProvider), ResourceFetcher<T> {

    val resourceLiveData: MutableStateFlow<Resource<T>> by lazy {
        MutableStateFlow<Resource<T>>(Resource.loading(LoadingType.REPLACE)).apply {
            viewModelScope.launch(dispatchersProvider.io) {
                emitAll(loadContent(LoadingType.REPLACE))
            }
        }
    }

    override fun tryAgain() {
        forceLoad()
    }

    open fun refresh() {
        forceLoad(LoadingType.REFRESH)
    }

    open fun forceLoad(loadingType: LoadingType = LoadingType.REPLACE) {
        launchMain {
            resourceLiveData.emitAll(loadContent(loadingType))
        }
    }

    protected abstract fun loadContent(loadingType: LoadingType): Flow<Resource<T>>
}