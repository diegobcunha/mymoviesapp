package com.br.diegocunha.mymovies.ui.main

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.br.diegocunha.mymovies.datasource.model.UpcomingMoviesResponse
import com.br.diegocunha.mymovies.ui.compose.theme.components.SubtitleColumn
import com.br.diegocunha.mymovies.ui.compose.theme.components.TitleColumn
import com.br.diegocunha.mymovies.ui.templates.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<UpcomingMoviesResponse>() {

    override val viewModel: MainViewModel by viewModel()

    @Composable
    override fun ApplyContent(viewState: UpcomingMoviesResponse?) {
        LoadMoviesGenre(viewState)
    }

    @Composable
    private fun LoadMoviesGenre(upComingMovie: UpcomingMoviesResponse?) {
        LazyColumn {
            items(upComingMovie?.results.orEmpty()) {
                SubtitleColumn(
                    title = it.title,
                    subtitle = it.releaseDate,
                    imageUrl = it.posterPath,
                    onClick = { navigateToGenreMovies(it.id) })
            }
        }
    }

    private fun navigateToGenreMovies(id: Long) {
        Log.i("MovieId", id.toString())
    }
}

