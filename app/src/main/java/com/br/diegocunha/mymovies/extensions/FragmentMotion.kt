package com.br.diegocunha.mymovies.extensions

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.br.diegocunha.mymovies.R
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

private const val DEFAULT_ANIM_DURATION = 300L

fun Fragment.setupPostEnterTransition() {
    postponeEnterTransition()
    view?.doOnPreDraw { startPostponedEnterTransition() }
}


/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Container Transform Transition
 *
 * @param destination navigation directions of FragmentB
 * @param startView the View that starts the transformation transition
 * @param endViewTransitionName the transitionName of the endView
 */
fun Fragment.navigateWithContainerTransform(
    destination: NavDirections,
    startView: View?,
    endViewTransitionName: String
) {
    if (startView != null) {

        exitTransition = MaterialElevationScale(false).apply {
            duration = DEFAULT_ANIM_DURATION
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = DEFAULT_ANIM_DURATION
        }

        val extras = FragmentNavigatorExtras(startView to endViewTransitionName)
        findNavController().navigate(destination, extras)
    } else {
        findNavController().navigate(destination)
    }
}


/**
 * Use this function in onViewCreated of FragmentB to setup a container transform enter transition
 *
 * @param viewTransitionName the transitionName of the Fragment B View, the same used in Fragment A to navigate
 * @param animDuration duration of animation transition.
 * @param drawingView view which will be made the transformation
 */
fun Fragment.setContainerTransformEnterTransition(
    viewTransitionName: String, animDuration: Long = DEFAULT_ANIM_DURATION,
    @IdRes drawingView: Int = R.id.fragmentContainer
) {
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        drawingViewId = drawingView
        duration = animDuration
        scrimColor = Color.TRANSPARENT
    }

    view?.transitionName = viewTransitionName
}


/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 * @param axis The Axis of transition. Should be: MaterialSharedAxis.X, MaterialSharedAxis.Y or MaterialSharedAxis.Z
 */
private fun Fragment.navigateWithSharedAxis(
    destination: NavDirections,
    @MaterialSharedAxis.Axis axis: Int,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialSharedAxis(axis, true).apply {
        duration = animDuration
    }
    reenterTransition = MaterialSharedAxis(axis, false).apply {
        duration = animDuration
    }
    findNavController().navigate(destination)
}


/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis X Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisX(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    navigateWithSharedAxis(destination, MaterialSharedAxis.X, animDuration)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Z Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisZ(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    navigateWithSharedAxis(destination, MaterialSharedAxis.Z, animDuration)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Z Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisZ(
    @IdRes destination: Int,
    bundle: Bundle? = null,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
        duration = animDuration
    }
    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
        duration = animDuration
    }
    findNavController().navigate(destination, bundle)
}

/**
 * Use this function at onCreate of the FragmentB to create a Shared Axis Transition with Fragment A
 *
 * @param animDuration duration of animation transition. It should be the same value of the Fragment A
 * @param axis The Axis of transition. Should be: MaterialSharedAxis.X, MaterialSharedAxis.Y or MaterialSharedAxis.Z
 */
fun Fragment.setSharedAxisEnterTransition(
    @MaterialSharedAxis.Axis axis: Int,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    enterTransition = MaterialSharedAxis(axis, true).apply {
        duration = animDuration
    }
    returnTransition = MaterialSharedAxis(axis, false).apply {
        duration = animDuration
    }
}


/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Fade Through Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithFadeThrough(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    reenterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    findNavController().navigate(destination)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Fade Through Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithFadeThrough(
    @IdRes destination: Int,
    bundle: Bundle? = null,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    reenterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    findNavController().navigate(destination, bundle)
}


/**
 * Use this function at onCreate of the Fragment to create a Fade Through enter Transition
 *
 * @param animDuration duration of animation transition. It should be the same value of the Fragment A
 */
fun Fragment.setFadeThroughEnterTransition(animDuration: Long = DEFAULT_ANIM_DURATION) {
    enterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }
}