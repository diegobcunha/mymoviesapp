package com.br.diegocunha.mymovies.ui.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import coil.compose.rememberImagePainter
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.ui.templates.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : BaseFragment<Movie>() {

    override val viewModel: MovieDetailViewModel by viewModel() {
        parametersOf(args.movieId)
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    @Composable
    override fun ApplyContent(viewState: Movie?) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = viewState?.posterPath,
                    builder = {
                        crossfade(true)

                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(text = viewState?.title.orEmpty())
        }
    }
}