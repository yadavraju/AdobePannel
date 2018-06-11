package com.raju.adobepannel

import android.animation.Animator
import android.view.ViewPropertyAnimator
import android.view.animation.Animation

/**
 * A util class to handle commonly used Animator methods.
 * Created by Raju Yadav.
 */
class AnimatorUtils {
    companion object {
        /**
         * Cancels the [ViewPropertyAnimator] with [ViewPropertyAnimator.cancel] if it isn't null.
         */
        fun cancel(animator: ViewPropertyAnimator?) {
            animator?.cancel()
        }

        /**
         * Cancels the [ViewPropertyAnimator] with [ViewPropertyAnimator.cancel] if it isn't null.
         */
        fun cancel(animator: Animator?) {
            animator?.cancel()
        }

        fun cancel(animator: Animation?) {
            animator?.cancel()
        }
    }
}