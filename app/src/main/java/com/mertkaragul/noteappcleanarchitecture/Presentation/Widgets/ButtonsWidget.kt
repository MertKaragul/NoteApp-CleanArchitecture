package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily

@Composable
fun NoteDialogButton(
    text : String,
    color: Color,
    onClicked : () -> Unit
) {
    Button(
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Text(
            text,
            style = TextStyle(
                fontFamily = noteFontFamily,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

