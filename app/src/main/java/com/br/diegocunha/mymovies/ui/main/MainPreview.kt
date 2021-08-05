package com.br.diegocunha.mymovies.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TmdbGreeting(clicked: Int) {
    Text("Hello: $clicked")
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    Column {
        ButtonClickable()
    }

}

@Composable
fun ButtonClickable() {
    val count = remember { mutableStateOf(0) }

    Button(onClick = { count.value++ }) {
        TmdbGreeting(count.value)
    }
}