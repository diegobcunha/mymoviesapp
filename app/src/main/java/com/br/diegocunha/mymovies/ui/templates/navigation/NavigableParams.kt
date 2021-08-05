package com.br.diegocunha.mymovies.ui.templates.navigation

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.NavigationRes
import com.br.diegocunha.mymovies.ui.templates.ActivityTransitionType

data class NavigableParams(
    val from: Activity,
    @NavigationRes val graphId: Int,
    val transitionType: ActivityTransitionType,
    val startView: View? = null,
    val block: (() -> Bundle)? = null
)
