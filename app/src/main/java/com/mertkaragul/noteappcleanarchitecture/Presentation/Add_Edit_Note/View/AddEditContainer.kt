package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily

@Composable
fun AddEditContainer(
    paddingValues: PaddingValues,
    title : String,
    titleChange : (String) -> Unit,
    description : String,
    descriptionChange : (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = title,
            onValueChange = titleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "Title",
                    style = TextStyle(
                        fontFamily = noteFontFamily,
                        fontSize = 35.sp
                    )
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontFamily = noteFontFamily,
                fontSize = 35.sp
            )
        )

        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = description,
            onValueChange = descriptionChange,
            placeholder = {
                Text(
                    "Description",
                    style = TextStyle(
                        fontFamily = noteFontFamily,
                        fontSize = 20.sp
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontFamily = noteFontFamily,
                fontSize = 20.sp
            )
        )
    }
}