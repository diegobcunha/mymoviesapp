package com.br.diegocunha.mymovies.ui.templates

enum class ScreenState {
    LOADING,
    SUCCESS,
    ERROR,
    ERROR_RETRY
}

fun ScreenState.isErrorState() =
    this == ScreenState.ERROR || this == ScreenState.ERROR_RETRY

fun ScreenState.isLoadingState() =
    this == ScreenState.LOADING || this == ScreenState.SUCCESS