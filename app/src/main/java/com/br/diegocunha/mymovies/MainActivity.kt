package com.br.diegocunha.mymovies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbGreeting()
        }
    }
}

@Composable
fun TmdbGreeting() {
    Text("Hello")
}
@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    TmdbGreeting()
}