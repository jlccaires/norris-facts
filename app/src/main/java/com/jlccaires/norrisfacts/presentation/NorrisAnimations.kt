package com.jlccaires.norrisfacts.presentation

import android.view.View
import android.view.animation.*
import kotlin.random.Random

class NorrisAnimations {
    companion object {

        @JvmStatic
        fun jokeInAnimation(v: View) {
            val animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f
            )

            animation.interpolator = AnticipateOvershootInterpolator()
            animation.duration = 500
            v.startAnimation(animation)
        }

        @JvmStatic
        fun jokeOutAnimation(v: View, listener: () -> Unit) {
            val scaleANimation = ScaleAnimation(
                1f,
                0f,
                1f,
                0f,
                Animation.RELATIVE_TO_PARENT,
                Random.nextFloat(),
                Animation.RELATIVE_TO_PARENT,
                Random.nextFloat()
            )
            val rotateAnimation = RotateAnimation(
                0f,
                Random.nextInt(-360, 360).toFloat(),
                (v.width / 2).toFloat(),
                (v.height / 2).toFloat()
            )

            val animations = AnimationSet(true)
            animations.duration = 200
            animations.addAnimation(scaleANimation)
            animations.addAnimation(rotateAnimation)
            animations.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    listener.invoke()
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
            v.startAnimation(animations)
        }

    }
}