package com.br.diegocunha.mymovies.ui.templates.viewmodel

import androidx.lifecycle.ViewModel


import androidx.lifecycle.viewModelScope
import com.br.diegocunha.mymovies.coroutines.DispatchersProvider
import com.br.diegocunha.mymovies.coroutines.ScopedContextDispatchers
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.datasource.resource.Resource.Companion.loading

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class CoroutineViewModel(val dispatchersProvider: DispatchersProvider): ViewModel() {

    private val scopedContextProvider =
        ScopedContextDispatchers(viewModelScope, dispatchersProvider)

    fun io() = scopedContextProvider.io
    fun main() = scopedContextProvider.main
    fun computing() = scopedContextProvider.computing

    fun launchIO(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(io(), start, block)

    fun launchMain(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(main(), start, block)

    fun launchComputing(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(computing(), start, block)

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start, block)
}
fun <T> CoroutineViewModel.createFetchCallBackFlow(
    fetchBlock: suspend () -> Resource<T>
) = callbackFlow<Resource<T>> {
    trySend(loading())
    trySend(fetchResource(fetchBlock))
}


private suspend fun <T> CoroutineViewModel.fetchResource(
    fetchBlock: suspend () -> Resource<T>
): Resource<T> {
    return try {
        val resource = withContext(io()) { fetchBlock() }
        withContext(main()) { resource }
    } catch (t: Throwable) {
        Resource.error(t)
    }
}
