package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View.Dialogs

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveDialog(
    showVisibility: Boolean,
    showVisibilityReq : (Boolean) -> Unit,
    saveButtonClicked : () -> Unit,
    cancelButtonClicked: () -> Unit
) {
    AnimatedVisibility(visible = showVisibility) {
        BasicAlertDialog(onDismissRequest = { showVisibilityReq(!showVisibility)  }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xff252525))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(10.dp),

                    )
                Text(
                    text = "Save Changes ?",
                    style = TextStyle(
                        fontFamily = noteFontFamily,
                        fontSize = 23.sp
                    )
                )

                Row(
                    modifier = Modifier.padding(5.dp)
                ){
                    Button(
                        onClick = {
                            showVisibilityReq(!showVisibility)
                            cancelButtonClicked()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffFF0000),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Cancel",
                            style = TextStyle(
                                fontFamily = noteFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .width(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(
                        onClick = {
                            showVisibilityReq(!showVisibility)
                            saveButtonClicked()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff30BE71),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Save",
                            style = TextStyle(
                                fontFamily = noteFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .width(50.dp)
                        )
                    }
                }
            }
        }
    }
}