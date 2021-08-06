package com.br.diegocunha.mymovies.ui

import com.br.diegocunha.mymovies.ui.main.MainViewModel
import com.br.diegocunha.mymovies.ui.movie.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get(), get()) }

    viewModel { (movieId: Long) -> MovieDetailViewModel(get(), movieId, get()) }
}