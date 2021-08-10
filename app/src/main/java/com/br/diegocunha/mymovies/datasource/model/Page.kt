package com.br.diegocunha.mymovies.datasource.model

interface Page {
    var page: Int
    val totalPages: Int
}

fun Int.increment() = this + 1