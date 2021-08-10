package com.br.diegocunha.mymovies.ui.compose.theme.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun TitleColumn(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier
            .padding(8.dp)
            .clickable { onClick.invoke() }
    ) {
        Text(title)
    }
}

@Composable
fun SubtitleColumn(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke() })
            .padding(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)

    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = RectangleShape
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null
            )
        }

        Column {
            Text(title, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(subtitle, style = MaterialTheme.typography.body2)
            }
        }
    }

    Divider()
}

@Composable
fun <T> InfiniteLazyColumn(
    listItems: List<T>,
    content: @Composable (T) -> Unit,
    onLoadMore: () -> Unit
) {
    val state = rememberLazyListState()
    val items = remember {
        mutableStateListOf<T>()
    }

    LazyColumn(
        state = state
    ) {

        items(items) { item ->
            content(item)
        }
    }

    InfiniteListHandler(listState = state) {
//        onLoadMore()
    }

    items.addAll(listItems)
}

@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onLoadMore()
                Log.e("StateScroll", it.toString())
            }
    }
}