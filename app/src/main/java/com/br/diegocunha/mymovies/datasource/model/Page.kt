package com.br.diegocunha.mymovies.datasource.model

import androidx.compose.runtime.MutableState

interface Page<T> {
    var page: Int
    val totalPages: Int
    val results: List<T>
}

fun MutableState<Int>.increment() = this.value + 1