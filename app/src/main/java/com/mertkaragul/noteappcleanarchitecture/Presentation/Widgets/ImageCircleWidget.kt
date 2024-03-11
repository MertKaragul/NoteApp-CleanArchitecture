package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import android.net.Uri
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mertkaragul.noteappcleanarchitecture.R

@Composable
fun ImageCircleWidget(
    width : Dp,
    height : Dp,
    image : Uri?,
    selectImage :() -> Unit
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
        .data(if (image == Uri.EMPTY || image == null) R.drawable.baseline_account_circle_24 else image)
        .build(),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(shape = CircleShape)
            .clickable {
                selectImage()
            },
    )
}