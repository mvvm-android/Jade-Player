// Copyright (c) 2019 . Wilberforce Uwadiegwu. All Rights Reserved.

package com.jadebyte.jadeplayer.common

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.res.Resources
import android.view.View

/**
 * Created by Wilberforce on 2019-05-05 at 18:59.
 */

/**
 *  Creates an alpha (0 to 1) and vertical transition (0,0 to [translationY]) [Animator] for this view
 *
 *   @param [translationY] The position to animate to
 *   @param [duration] How long the animation should run
 *   @param [startDelay] How long to wait before running the animation
 *
 *   @return the built animator
 */
fun View.fadeInSlideUp(translationY: Float = 300F, duration: Long = 1000, startDelay: Long = 0): Animator {
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0F, 1F)
    val translateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, translationY, 0F)
    val animator = ObjectAnimator.ofPropertyValuesHolder(this, alpha, translateY)
    animator.duration = duration
    animator.startDelay = startDelay
    return animator
}


/**
 *  Creates an alpha (0 to 1) and vertical transition ([translationY] to 0,0) [Animator] for this view
 *
 *   @param [translationY] The position to animate to.Any value passed will be negated
 *   @param [duration] How long the animation should run
 *   @param [startDelay] How long to wait before running the animation
 *
 *   @return the built animator
 */
fun View.fadeInSlideDown(translationY: Float = -300F, duration: Long = 1000, startDelay: Long = 0): Animator {
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0F, 1F)
    val translateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -Math.abs(translationY), 0F)
    val animator = ObjectAnimator.ofPropertyValuesHolder(this, alpha, translateY)
    animator.duration = duration
    animator.startDelay = startDelay
    return animator
}

/**
 *  Creates an alpha (1 to 0) and vertical transition (0,0 to [translationY]) [Animator] for this view
 *
 *   @param [translationY] The position to animate to. Any value passed will be negated
 *   @param [duration] How long the animation should run
 *   @param [startDelay] How long to wait before running the animation
 *
 *   @return the built animator
 */
fun View.fadeOutSlideUp(translationY: Float = -300F, duration: Long = 1000, startDelay: Long = 0): Animator {
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1F, 0F)
    val translateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0F, -Math.abs(translationY))
    val animator = ObjectAnimator.ofPropertyValuesHolder(this, alpha, translateY)
    animator.duration = duration
    animator.startDelay = startDelay
    return animator
}

/**
 *  Creates an alpha (1 to 0) and vertical transition ([translationY] to 0,0) [Animator] for this view
 *
 *   @param [translationY] The position to animate to.
 *   @param [duration] How long the animation should run
 *   @param [startDelay] How long to wait before running the animation
 *
 *   @return the built animator
 */
fun View.fadeOutSlideDown(translationY: Float = 300F, duration: Long = 1000, startDelay: Long = 0): Animator {
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1F, 0F)
    val translateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0F, translationY)
    val animator = ObjectAnimator.ofPropertyValuesHolder(this, alpha, translateY)
    animator.duration = duration
    animator.startDelay = startDelay
    return animator
}

fun View.fadeOut(duration: Long = 1000, startDelay: Long = 0): Animator {
    val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 1F, 0F)
    animator.duration = duration
    animator.startDelay = startDelay
    return animator
}


/**
 * Converts an Int in pixels to dp(density independent pixels)
 */
val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Converts an Int in dp(density independent pixels) to pixels
 */
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Converts an Float in pixels to dp(density independent pixels)
 */
val Float.dp: Float get() = (this / Resources.getSystem().displayMetrics.density)

/**
 * Converts an Float in dp(density independent pixels) to pixels
 */
val Float.px: Float get() = (this * Resources.getSystem().displayMetrics.density)