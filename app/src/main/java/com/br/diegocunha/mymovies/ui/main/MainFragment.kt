package com.br.diegocunha.mymovies.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.templates.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    override val viewModel: MainViewModel by viewModel()

    @Composable
    override fun ApplyContent() {
        LoadMovieItems(viewModel = viewModel)
    }
}

@Composable
fun LoadMovieItems(viewModel: MainViewModel) {
    val viewState =
        viewModel.resourceLiveData.collectAsState(initial = Resource.Loading(LoadingType.REPLACE))

    when (viewState.value) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Error -> {
            Button(onClick = { viewModel.refresh() }) {
                Text("Tentar novamente")
            }
        }
        is Resource.Success -> {
            LazyColumn {
                items(viewState.value.data?.genres.orEmpty()) { movie ->
                    Text(text = movie.name)
                }
            }
        }
    }
}