package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Common.NoteStatus
import com.mertkaragul.noteappcleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopBar(
    rememberNavHostController : NavHostController,
    savePress : () -> Unit,
    deletePress : () -> Unit,
    saveableNote : Boolean
) {
    return TopAppBar(
        title = {},
        navigationIcon = {
            Button(
                onClick = {
                    rememberNavHostController.popBackStack()
                },
                shape = RoundedCornerShape(7.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back to main page",
                )
            }
        },
        actions = {
            Button(
                onClick = savePress,
                shape = RoundedCornerShape(7.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save or update note"
                )
            }

            if(saveableNote){
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    ),

                    onClick = deletePress,
                    shape = RoundedCornerShape(7.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = "Delete note"
                    )
                }
            }
        }
    )
}