package com.br.diegocunha.mymovies.ui.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TmdbTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme() {
       content()
   }
}