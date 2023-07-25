package com.example.mir_test_task.View


import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import com.example.mir_test_task.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime
import kotlin.time.seconds


internal class ActionMenu() {
    @Composable
    private fun FloationgActionMenus(actionMenuScale: Float, isOpen: Boolean) {
        for (i in 0..4) {
            var time: Double
            var resource : Int
            val showButton by remember { mutableStateOf(false) }
            when (i) {
                0 -> {
                    resource = R.drawable.ic_baseline_settings_24
                    time = 0.7
                }
                1 -> {
                    resource = R.drawable.ic_baseline_home_24
                    time = 0.55
                }
                2 -> {
                    resource = R.drawable.ic_baseline_search_24
                    time = 0.4
                }
                3 -> {
                    resource = R.drawable.ic_baseline_access_time_24
                    time = 0.2
                }
                else -> {
                    resource = R.drawable.ic_baseline_widgets_24
                    time = 0.0

                }
            }
            FormButton(
                isOpen = isOpen,
                showButton = showButton,
                actionMenuScale = actionMenuScale,
                resource = resource,
                time = time
            )
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    fun FormButton(isOpen: Boolean, showButton: Boolean, actionMenuScale: Float, resource: Int, time: Double) {
        var currentShow by remember { mutableStateOf(showButton) }
        if (isOpen) {
            LaunchedEffect(Unit) {
                delay(time.seconds)
                currentShow = true
            }
            if (currentShow) {
             ButtonConfig(resource = resource, float = actionMenuScale).SwipeButton()
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
        else if (countSwipes < 5) {
            if (currentShow) {
                ButtonConfig(resource = resource, float = actionMenuScale).SwipeButton()
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
            LaunchedEffect(Unit) {
                delay((0.7 - time).seconds)
                currentShow = false
            }
        }
        else {
            currentShow = false
        }
    }

    @Composable
    fun FloatingActionButtonLayout(isOpen: Boolean, onToggle: () -> Unit, onClose: () -> Unit) {
        val transition = updateTransition(targetState = isOpen, label = "")
        val actionMenuScale = Animation().TransitionAnimation(
            transition = transition,
            valueForTrue = 1f,
            valueForFalse = 0f
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.background),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 25.dp)
                ) {
                    FloationgActionMenus(actionMenuScale = actionMenuScale, isOpen)
                    if (countSwipes == 5) {
                        onToggle()
                    }
                }
                BottomAppBar(
                    backgroundColor = Color(0, 151, 131),
                ) {
                    Spacer(Modifier.weight(1f, true))
                    TextButton(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        onClick = {
                            if (isOpen) {
                                onClose()
                            } else {
                                onToggle()
                            }
                        },
                    ) {
                        Text(
                            text = "Menu",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                    }
                }
            }
        }
    }
}