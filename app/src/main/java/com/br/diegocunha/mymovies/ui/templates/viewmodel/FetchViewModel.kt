package com.br.diegocunha.mymovies.ui.templates.viewmodel

import com.br.diegocunha.mymovies.coroutines.DispatchersProvider

abstract class FetchViewModel<I>(dispatchersProvider: DispatchersProvider) :
    ResourceViewModel<I>(dispatchersProvider) {

    protected open var lastRequestedValue: I? = null

    protected open fun fetchContext() = dispatchersProvider.io

    protected open fun liveDataContext() = main()

    protected open fun toViewContext() = dispatchersProvider.computing
}