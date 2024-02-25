package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteButtonWidget(
    text : String,
    onClick : () -> Unit,
    modifier : Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier ) {
        Text(
           text = text,
        )
    }
}