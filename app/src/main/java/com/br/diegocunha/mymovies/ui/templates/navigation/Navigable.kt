package com.br.diegocunha.mymovies.ui.templates.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NavigationRes
import com.br.diegocunha.mymovies.extensions.startActivityWithTransition

/**
 * Base interface for activities that use NavigationComponent.
 * The [T] and [classType] are just a workaround so we can use the type dynamically as a parameter
 * for the intent.
 */
interface Navigable<T : Activity> {

    val classType: Class<T>

    fun launch(
        from: Context,
        @NavigationRes graphId: Int,
        block: (() -> Bundle)? = null
    ) {
        val bundle = createBundleExtra(graphId, block)
        val intent = Intent(from, classType)
        intent.putExtras(bundle)
        from.startActivity(intent)
    }

    fun launchWithTransition(params: NavigableParams) {
        with(params) {
            val bundle = createBundleExtra(graphId, block)
            val intent = Intent(from, classType)
            intent.putExtras(bundle)
            from.startActivityWithTransition(intent, transitionType, startView)
        }
    }

    private fun createBundleExtra(
        @NavigationRes graphId: Int,
        block: (() -> Bundle)? = null
    ) : Bundle {
        return (block?.invoke() ?: Bundle()).apply {
            putInt(GRAPH_ID_KEY, graphId)
        }
    }

    companion object {
        const val GRAPH_ID_KEY = "GRAPH_ID_KEY"
    }
}