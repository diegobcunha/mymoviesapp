package com.br.diegocunha.mymovies.ui.templates.fragment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.diegocunha.mymovies.datasource.model.Page
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.ui.compose.theme.components.CircularIndeterminateProgressBar
import com.br.diegocunha.mymovies.ui.templates.viewmodel.PaginableViewModel
import com.br.diegocunha.mymovies.ui.templates.viewmodel.PaginableViewModel.Companion.PAGE_SIZE

abstract class PaginableFragment<O, T : Page<O>> : BaseFragment<T>() {

    abstract override val viewModel: PaginableViewModel<O, T>

    @Composable
    abstract fun ApplyListContent(content: O)

    @Composable
    override fun ApplyContent(viewState: T?) {
        val state = viewModel.resourceStateFlow.collectAsState()
        LazyColumn {
            itemsIndexed(viewModel.items) { index, item ->
                if ((index + 1) >= (viewModel.currentPage.value * PAGE_SIZE) && !viewModel.resourceStateFlow.value.isLoading()) {
                    viewModel.loadNextPage()
                }
                ApplyListContent(content = item)
            }
        }
    }
}