package com.example.mir_test_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.example.mir_test_task.View.ActionMenu
import com.example.mir_test_task.View.countSwipes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    private fun ContentView() {
        var isOpen by remember {
            mutableStateOf(false)
        }
        ActionMenu().FloatingActionButtonLayout(
            isOpen = isOpen,
            onToggle = {
                countSwipes = 0
                isOpen = true

            },
            onClose = {
                isOpen = false
            }
        )
    }
}




