package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
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
    getNoteId : Int? = null,
    rememberNavHostController: NavHostController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.editModel


    val title = if( state.value.data != null) remember{ mutableStateOf(state.value.data!!.title) }
    else remember{ mutableStateOf("") }


    val description = if(state.value.data != null) remember{ mutableStateOf(state.value.data!!.description) }
    else remember{ mutableStateOf("") }

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
                    value = title.value,
                    onValueChange = {
                        text -> title.value = text
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
                    value = description.value,
                    onValueChange = {
                            text -> description.value = text
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
                                id = state.value.data?.id ?: 0,
                                title = title.value,
                                shortDesc = "",
                                description = description.value,
                                image = "",
                                color = color
                            ),
                            state.value.editMode
                        )
                    )
                },
                showVisibilityReq = { status ->
                    showVisibility = status
                }
            )
        }
    )

    // User want to edit note ?
    LaunchedEffect(key1 = Unit) {
        if(getNoteId != null){
            viewModel.onEvent(
                AddEditEvent.GetNoteById(
                    noteId = getNoteId
                )
            )
        }
    }

}