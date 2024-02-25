package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextFieldWidgetOutline(
    text : String,
    textChanged : (String) -> Unit,
    placeholder:String,
    errorMessage : String
) {
    val error : Boolean = text.isEmpty()
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = textChanged,
            placeholder = {
                Text(
                    placeholder
                )
            },
            isError = error,
            colors = TextFieldDefaults.colors(
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                errorTextColor = MaterialTheme.colorScheme.error,
                errorCursorColor = MaterialTheme.colorScheme.error,
                errorPlaceholderColor = MaterialTheme.colorScheme.error
            )
        )

        if(error){
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.W600
            )
        }

    }
}