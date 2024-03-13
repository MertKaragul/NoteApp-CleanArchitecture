package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View.Dialogs.SaveDialog
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import com.mertkaragul.noteappcleanarchitecture.R
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    jsonModel : String? = null,
    rememberNavHostController: NavHostController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.editModel

    var title by remember { mutableStateOf((state.value.data?.title ?: "")) }
    var description by remember { mutableStateOf((state.value.data?.description ?: "")) }
    var showVisibility by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
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
                            contentDescription = "",
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = {
                                  showVisibility = true
                        },
                        shape = RoundedCornerShape(7.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_save_24),
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                TextField(
                    value = title,
                    onValueChange = {
                        text -> title = text
                    },
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
                    onValueChange = {
                            text -> description = text
                    },
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

            SaveDialog(
                showVisibility = showVisibility,
                cancelButtonClicked = {
                    // cancel
                },
                saveButtonClicked = {
                    val rnd = Random.Default
                    val color: Int = android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                    viewModel.onEvent(
                        AddEditEvent.SaveOrEditNote(
                            NoteModelDto(
                                id = 0,
                                title = title,
                                shortDesc = "",
                                description = description,
                                image = "",
                                color = color
                            ),
                            false
                        )
                    )
                },
                showVisibilityReq = {status ->
                    showVisibility = status
                }
            )
            it.calculateBottomPadding()
        }
    )
}