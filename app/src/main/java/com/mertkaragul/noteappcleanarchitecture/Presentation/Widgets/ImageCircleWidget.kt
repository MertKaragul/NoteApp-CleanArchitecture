package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImageCircleWidget(
    width : Dp,
    height : Dp,
    image : Int,
    selectImage : () -> Unit
) {
    Column(
        modifier = Modifier
            .width(width)
            .height(height)
            .border(2.dp, Color.White, CircleShape)
            .padding(1.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .clickable {
                selectImage()
            }
    ) {
        if(image != 0){
            Image(painter = painterResource(id = image) , contentDescription = "")
        }
    }
}