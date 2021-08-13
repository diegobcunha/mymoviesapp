package com.br.diegocunha.mymovies.ui.compose.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

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
        Image(
            modifier = Modifier.size(50.dp),
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null
        )

        Column(Modifier.padding(start = 16.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(subtitle, style = MaterialTheme.typography.body2)
            }
        }
    }

    Divider()
}