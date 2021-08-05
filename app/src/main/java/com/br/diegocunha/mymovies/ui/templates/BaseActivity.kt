package com.br.diegocunha.mymovies.ui.templates

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.br.diegocunha.mymovies.R
import com.br.diegocunha.mymovies.extensions.getEnumExtra
import com.br.diegocunha.mymovies.extensions.setToolbar
import com.br.diegocunha.mymovies.extensions.setupContainerTransformEnterAnimation
import com.br.diegocunha.mymovies.extensions.setupElevationScaleEnterAnimation
import com.br.diegocunha.mymovies.extensions.setupFadeThroughEnterAnimation

abstract class BaseActivity: AppCompatActivity() {

//    val toolbar: Toolbar? by lazy { findViewById(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
//        toolbar?.let {
//            setupToolbar(it)
//        }

        setupEnterAnimationIfNeeded()
    }

    protected open fun setupToolbar(toolbar: Toolbar) {
        setToolbar(toolbar)
    }

    private fun setupEnterAnimationIfNeeded() {
        when (intent?.getEnumExtra<ActivityTransitionType>(ACTIVITY_TRANSITION_TYPE)) {
            ActivityTransitionType.CONTAINER_TRANSFORM -> {
                setupContainerTransformEnterAnimation(ACTIVITY_ROOT_VIEW_TRANSITION_NAME)
            }
            ActivityTransitionType.ELEVATION_SCALE -> {
                setupElevationScaleEnterAnimation()
            }
            ActivityTransitionType.FADE_THROUGH -> {
                setupFadeThroughEnterAnimation()
            }
        }
    }

    companion object {
        const val ACTIVITY_TRANSITION_TYPE = "ACTIVITY_TRANSITION_TYPE"
        const val ACTIVITY_ROOT_VIEW_TRANSITION_NAME = "ACTIVITY_ROOT_VIEW_TRANSITION_NAME"
    }

}