package com.br.diegocunha.mymovies.ui.compose.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HelperComponent(onRetryClick: (() -> Unit)? = null, isLoading: Boolean) {

    var loadingState by remember { mutableStateOf(isLoading) }

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }

        Text(
            AnnotatedString("Erro", ParagraphStyle(TextAlign.Center)),
            Modifier
                .padding(bottom = 8.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        Text(
            AnnotatedString("Message", ParagraphStyle(TextAlign.Center)),
            Modifier
                .fillMaxWidth()
                .padding(start = 48.dp, end = 48.dp, bottom = 16.dp),
            maxLines = 5
        )

        Button(onClick = {
            onRetryClick?.let {
                loadingState = true
                it.invoke()
            }
        },) {
            if (loadingState) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.error
                )
            } else {
                Text("Tentar novamente")
            }
        }
    }
}