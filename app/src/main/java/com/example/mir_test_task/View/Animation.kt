package com.example.mir_test_task.View

import android.annotation.SuppressLint
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

internal class Animation() {

    @SuppressLint("ComposableNaming")
    @Composable
    fun TransitionAnimation(transition: Transition<Boolean>, valueForTrue: Float, valueForFalse: Float): Float {
        val animationValue: Float by transition.animateFloat(
            transitionSpec = {
                tween(durationMillis = 750)
            },
            label = ""
        ) {
            if (it) {
                valueForTrue
            }
            else {
                valueForFalse
            }
        }
        return animationValue
    }
}