package com.example.mir_test_task.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

var countSwipes = 0
internal class ButtonConfig(private val resource: Int, val float: Float) {

    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun SwipeButton() {
        val list = mutableStateListOf("button")
        LazyColumn(
            modifier = Modifier.wrapContentWidth()
        ) {
            itemsIndexed(items = list, key = { _, listItem ->
                listItem.hashCode()
            }) { _, item ->
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            list.remove(item)
                            countSwipes += 1
                        }
                        true
                    }

                )

                SwipeToDismiss(
                    state = state,
                    background = { },
                    dismissContent = {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0, 151, 131)),
                            shape = CircleShape,
                            modifier = Modifier
                                .size(70.dp)
                                .scale(float)
                        ) {
                            Image(
                                painter = painterResource(resource),
                                contentDescription = "base-icon",
                                modifier = Modifier.size(60.dp),
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd)
                )

            }
        }
    }
}