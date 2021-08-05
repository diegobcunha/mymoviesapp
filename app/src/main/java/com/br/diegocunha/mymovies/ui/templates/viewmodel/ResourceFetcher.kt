package com.br.diegocunha.mymovies.ui.templates.viewmodel

interface ResourceFetcher<T> {

    /**
     * try load again
     */
    fun tryAgain()
}