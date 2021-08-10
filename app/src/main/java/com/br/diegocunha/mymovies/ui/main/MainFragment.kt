package com.br.diegocunha.mymovies.ui.main

import androidx.compose.runtime.Composable
import com.br.diegocunha.mymovies.datasource.model.UpcomingMoviesResponse
import com.br.diegocunha.mymovies.extensions.navigateWithSharedAxisX
import com.br.diegocunha.mymovies.ui.compose.theme.components.InfiniteLazyColumn
import com.br.diegocunha.mymovies.ui.compose.theme.components.SubtitleColumn
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
        InfiniteLazyColumn(
            listItems = upComingMovie?.results.orEmpty(),
            onLoadMore = { viewModel.loadNextPage() },
            content = {
                SubtitleColumn(
                    title = it.title,
                    subtitle = it.releaseDate,
                    imageUrl = it.posterPath,
                    onClick = { navigateToGenreMovies(it.id) })
            }
        )
    }

    private fun navigateToGenreMovies(id: Long) {
        val directions = MainFragmentDirections.actionMainFragmentToMovieDetailFragment(id)
        navigateWithSharedAxisX(directions)
    }
}

