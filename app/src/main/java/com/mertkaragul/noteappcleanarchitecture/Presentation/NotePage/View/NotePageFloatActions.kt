package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Common.NoteStatus
import com.mertkaragul.noteappcleanarchitecture.Common.Routes
import com.mertkaragul.noteappcleanarchitecture.R

@Composable
fun NotePageFloatActions(
    onClick : () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_add_24) ,
            contentDescription = "Add note"
        )
    }
}