package com.br.diegocunha.mymovies.ui.movie

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.fragment.navArgs
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.ui.templates.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment: BaseFragment<Movie>() {


    override val viewModel: MovieDetailViewModel by viewModel() {
        parametersOf(args.movieId)
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    @Composable
    override fun ApplyContent(viewState: Movie?) {
        Text("Applied with success")
    }
}