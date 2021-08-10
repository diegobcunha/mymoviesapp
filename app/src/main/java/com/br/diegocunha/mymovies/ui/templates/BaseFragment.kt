package com.br.diegocunha.mymovies.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.compose.theme.TmdbTheme
import com.br.diegocunha.mymovies.ui.compose.theme.components.HelperComponent
import com.br.diegocunha.mymovies.ui.compose.theme.components.ShimmerLoader
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
            viewModel.resourceStateFlow.collectAsState()

        var isLoading by remember { mutableStateOf(viewState.value.isLoading()) }
        var lastState by remember { mutableStateOf(ScreenState.LOADING) }

        lastState = getScreenState(viewState.value, lastState)

        when (lastState) {
            ScreenState.LOADING -> LoadingState((viewState.value as Resource.Loading).type)
            ScreenState.SUCCESS -> ApplyContent(viewState.value.data)
            ScreenState.ERROR,
            ScreenState.ERROR_RETRY -> {
                isLoading = viewState.value.isLoading()
                ErrorState(throwable = null, isLoading = isLoading)
            }
        }
    }

    @Composable
    protected fun LoadingState(state: LoadingType?) {
        when (state) {
            LoadingType.REPLACE -> ShimmerLoader()
            else -> Unit
        }
    }

    @Composable
    protected fun ErrorState(throwable: Throwable?, isLoading: Boolean) {
        HelperComponent(isLoading = isLoading, onRetryClick = { viewModel.forceLoad() })
    }

    private fun getScreenState(apiState: Resource<T>, screenState: ScreenState): ScreenState {
        return when {
            apiState.isLoading() && screenState.isErrorState() -> ScreenState.ERROR_RETRY
            apiState.isSuccess() -> ScreenState.SUCCESS
            apiState.isError() -> ScreenState.ERROR
            apiState.isLoading() && screenState.isLoadingState() -> ScreenState.LOADING
            else -> {
                throw Exception("Faltou algo: $apiState + $screenState")
            }
        }
    }
}