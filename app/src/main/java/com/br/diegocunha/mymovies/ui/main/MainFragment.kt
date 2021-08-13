package com.br.diegocunha.mymovies.ui.main

import androidx.compose.runtime.Composable
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.datasource.model.UpcomingMoviesResponse
import com.br.diegocunha.mymovies.extensions.navigateWithSharedAxisX
import com.br.diegocunha.mymovies.ui.compose.theme.components.SubtitleColumn
import com.br.diegocunha.mymovies.ui.templates.fragment.PaginableFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : PaginableFragment<Movie, UpcomingMoviesResponse>() {

    override val viewModel: MainViewModel by viewModel()

    @Composable
    override fun ApplyListContent(content: Movie) {
        SubtitleColumn(
            title = content.title,
            subtitle = content.releaseDate,
            imageUrl = content.posterPath,
            onClick = { navigateToGenreMovies(content.id) })
    }

    private fun navigateToGenreMovies(id: Long) {
        val directions = MainFragmentDirections.actionMainFragmentToMovieDetailFragment(id)
        navigateWithSharedAxisX(directions)
    }
}

