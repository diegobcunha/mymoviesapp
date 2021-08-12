package com.br.diegocunha.mymovies.ui.templates.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.datasource.resource.Resource
import com.br.diegocunha.mymovies.ui.compose.theme.TmdbTheme
import com.br.diegocunha.mymovies.ui.compose.theme.components.HelperComponent
import com.br.diegocunha.mymovies.ui.compose.theme.components.ShimmerLoader
import com.br.diegocunha.mymovies.ui.templates.ScreenState
import com.br.diegocunha.mymovies.ui.templates.isErrorState
import com.br.diegocunha.mymovies.ui.templates.isLoadingState
import com.br.diegocunha.mymovies.ui.templates.isUpdatingState
import com.br.diegocunha.mymovies.ui.templates.viewmodel.ResourceViewModel

abstract class BaseFragment<T> : Fragment() {

    abstract val viewModel: ResourceViewModel<T>

    @Composable
    abstract fun ApplyContent(viewState: T?, loadingType: LoadingType?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            TmdbTheme {
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    ResourceState()
                }
            }
        }
    }

    @Composable
    private fun ResourceState() {
        val viewState =
            viewModel.resourceStateFlow.collectAsState()

        var isLoading by rememberSaveable { mutableStateOf(viewState.value.isLoading()) }
        var lastState by rememberSaveable { mutableStateOf(ScreenState.LOADING) }

        lastState = getScreenState(viewState.value, lastState)

        when (lastState) {
            ScreenState.LOADING -> LoadingState(viewState.value.loadingType())
            ScreenState.UPDATE,
            ScreenState.SUCCESS -> {
                ApplyContent(viewState.value.data, viewState.value.loadingType())
            }
            ScreenState.ERROR,
            ScreenState.ERROR_RETRY -> {
                isLoading = viewState.value.isLoading()
                ErrorState(throwable = viewState.value.getThrowableOrNull(), isLoading = isLoading)
            }
        }
    }

    @Composable
    protected fun LoadingState(state: LoadingType?) {
        when (state) {
            LoadingType.REPLACE -> ShimmerLoader()
        }
    }

    @Composable
    protected fun ErrorState(throwable: Throwable?, isLoading: Boolean) {
        Log.e("BaseFragment", null, throwable)
        HelperComponent(isLoading = isLoading, onRetryClick = { viewModel.forceLoad() })
    }

    private fun getScreenState(apiState: Resource<T>, screenState: ScreenState): ScreenState {
        return when {
            apiState.isLoading() && screenState.isErrorState() -> ScreenState.ERROR_RETRY
            apiState.isSuccess() -> ScreenState.SUCCESS
            apiState.isError() -> ScreenState.ERROR
            apiState.isLoading() && screenState.isUpdatingState() && apiState.isPaginationLoadingType() -> ScreenState.UPDATE
            apiState.isLoading() && screenState.isLoadingState() -> ScreenState.LOADING
            else -> {
                throw Exception("Faltou algo: $apiState + $screenState")
            }
        }
    }
}