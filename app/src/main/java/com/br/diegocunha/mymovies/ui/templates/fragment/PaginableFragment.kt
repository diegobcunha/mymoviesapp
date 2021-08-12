package com.br.diegocunha.mymovies.ui.templates.fragment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.br.diegocunha.mymovies.datasource.model.Page
import com.br.diegocunha.mymovies.datasource.resource.LoadingType
import com.br.diegocunha.mymovies.ui.compose.theme.components.CircularIndeterminateProgressBar
import com.br.diegocunha.mymovies.ui.templates.viewmodel.PaginableViewModel
import com.br.diegocunha.mymovies.ui.templates.viewmodel.PaginableViewModel.Companion.PAGE_SIZE

abstract class PaginableFragment<O, R : Page<O>> : BaseFragment<R>() {

    abstract override val viewModel: PaginableViewModel<O, R>

    @Composable
    abstract fun ApplyListContent(content: O)

    @Composable
    override fun ApplyContent(viewState: R?, loadingType: LoadingType?) {
        val stateList = rememberLazyListState()
        var isLoadingPagination by rememberSaveable { mutableStateOf(false) }

        isLoadingPagination = loadingType != null && loadingType == LoadingType.PAGINATION

        LazyColumn(state = stateList) {
            itemsIndexed(viewModel.items) { index, item ->
                viewModel.setListScrollPosition(index)
                if ((index + 1) >= (viewModel.currentPage.value * PAGE_SIZE) && !viewModel.resourceStateFlow.value.isLoading()) {
                    viewModel.loadNextPage()
                }

                ApplyListContent(content = item)
            }
        }

        CircularIndeterminateProgressBar(isLoadingPagination, 0.3f)
    }
}