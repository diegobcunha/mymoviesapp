package com.br.diegocunha.mymovies.ui.compose.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChipText(text: String?) {
    Card(
        Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(32.dp),
        backgroundColor = Color.Red
    ) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = text.orEmpty()
        )
    }
}