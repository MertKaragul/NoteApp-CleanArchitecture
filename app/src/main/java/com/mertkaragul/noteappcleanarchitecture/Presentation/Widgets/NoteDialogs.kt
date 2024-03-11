package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog

@Composable
fun NoteDialogs(
    title : String,
    description : String,
    buttons : () -> Unit
) {
    Dialog(
        onDismissRequest = {

        },
        content = {
            buttons()
        }
    )
}