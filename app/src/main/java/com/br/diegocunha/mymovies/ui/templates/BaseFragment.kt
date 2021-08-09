package com.br.diegocunha.mymovies.ui.templates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.compose.theme.TmdbTheme
import com.br.diegocunha.mymovies.ui.compose.theme.components.BasicLoader
import com.br.diegocunha.mymovies.ui.compose.theme.components.HelperComponent
import com.br.diegocunha.mymovies.ui.templates.viewmodel.ResourceViewModel

abstract class BaseFragment<T> : Fragment() {

    abstract val viewModel: ResourceViewModel<T>

    @Composable
    abstract fun ApplyContent(viewState: T?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            TmdbTheme {
                ResourceState()
            }
        }
    }

    @Composable
    private fun ResourceState() {
        val viewState =
            viewModel.resourceLiveData.collectAsState()

        val isLoading by remember { mutableStateOf(viewState.value.isLoading()) }
        Log.i("LoadingState", isLoading.toString())

        when (viewState.value) {
            is Resource.Loading -> LoadingState()
            is Resource.Error -> ErrorState(viewState.value.getThrowableOrNull(), isLoading)
            is Resource.Success -> {
                ApplyContent(viewState.value.data)
            }
        }
    }

    @Composable
    protected fun LoadingState(state: LoadingType? = LoadingType.REPLACE) {
        when(state) {
            LoadingType.REPLACE -> BasicLoader()
            else -> Unit
        }
    }

    @Composable
    protected fun ErrorState(throwable: Throwable?, isLoading: Boolean) {
        HelperComponent(isLoading = isLoading, onRetryClick = { viewModel.forceLoad() })
    }
}