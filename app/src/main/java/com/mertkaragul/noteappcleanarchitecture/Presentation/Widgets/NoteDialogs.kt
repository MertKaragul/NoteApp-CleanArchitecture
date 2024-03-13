package com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialogs(
    title : String,
    sendBackVisibility : (Boolean) -> Unit,
    icon : ImageVector = Icons.Default.Info,
    buttons : @Composable () -> Unit
) {
    BasicAlertDialog(onDismissRequest = { sendBackVisibility.invoke(false) }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xff252525))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp),

                )
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = noteFontFamily,
                    fontSize = 23.sp
                )
            )

            Row(
                modifier = Modifier.padding(5.dp)
            ){
                buttons()
            }
        }
    }
}