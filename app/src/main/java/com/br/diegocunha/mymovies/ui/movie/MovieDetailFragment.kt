package com.br.diegocunha.mymovies.ui.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.ImagePainter.ExecuteCallback
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.br.diegocunha.mymovies.datasource.model.Movie
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.ui.compose.theme.components.ChipText
import com.br.diegocunha.mymovies.ui.templates.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : BaseFragment<Movie>() {

    override val viewModel: MovieDetailViewModel by viewModel() {
        parametersOf(args.movieId)
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    @ExperimentalCoilApi
    @Composable
    override fun ApplyContent(viewState: Movie?, loadingType: LoadingType?) {
        Column {
            Image(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp),
                painter = rememberImagePainter(
                    data = viewState?.posterPath,
                    imageLoader = LocalImageLoader.current,
                    builder = {
                        crossfade(true)
                        scale(Scale.FIT)
                    }
                ),
                contentDescription = null
            )
            Text(text = viewState?.title.orEmpty())
            Row {
                viewState?.genres?.forEach {
                    ChipText(text = (it.name))
                }
            }
        }
    }
}