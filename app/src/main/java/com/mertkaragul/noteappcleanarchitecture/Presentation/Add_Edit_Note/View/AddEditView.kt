package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.mertkaragul.noteappcleanarchitecture.Common.NoteStatus
import com.mertkaragul.noteappcleanarchitecture.Common.Routes
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteDialogButton
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteDialogs
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import com.mertkaragul.noteappcleanarchitecture.R
import okhttp3.Route
import java.security.KeyStore.TrustedCertificateEntry
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    getNoteId : Int = NoteStatus.NEW_NOTE.ordinal,
    rememberNavHostController: NavHostController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.editModel


    val title = if(state.value.data != null ) remember{ mutableStateOf(state.value.data!!.title) }
    else remember{ mutableStateOf("") }


    val description = if(state.value.data != null ) remember{ mutableStateOf(state.value.data!!.description) }
    else remember{ mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var whichWantDo by remember { mutableStateOf(NoteStatus.SAVE_NOTE) }


    Scaffold(
        topBar = {
            AddEditTopBar(
                rememberNavHostController = rememberNavHostController,
                savePress = {
                    showDialog = !showDialog
                    whichWantDo = NoteStatus.NEW_NOTE
                },
                deletePress = {
                    showDialog = !showDialog
                    whichWantDo = NoteStatus.DELETE_NOTE
                },
                saveableNote = getNoteId != NoteStatus.NEW_NOTE.ordinal
            )
        },
        content = {
            AddEditContainer(
                paddingValues = it,
                title = title.value,
                titleChange = {titleChanged -> title.value = titleChanged},
                description = description.value,
                descriptionChange = {descChanges -> description.value = descChanges}
            )

            // Page dialog \\
            AnimatedVisibility(visible = (showDialog)) {
                NoteDialogs(
                    title = if(whichWantDo == NoteStatus.SAVE_NOTE || whichWantDo == NoteStatus.NEW_NOTE) "Save changes?" else "Are you sure delete note?",
                    sendBackVisibility = { showDialog = !showDialog },
                    buttons = {
                        NoteDialogButton(
                            text = "Cancel",
                            color = Color.Red,
                            onClicked = {
                                showDialog = !showDialog
                            }
                        )
                        Spacer(modifier = Modifier.padding(3.dp))
                        NoteDialogButton(
                            text = if(whichWantDo == NoteStatus.SAVE_NOTE || whichWantDo == NoteStatus.NEW_NOTE) "Save" else "Delete",
                            color = Color(0xff30BE71),
                            onClicked = {
                                if(whichWantDo == NoteStatus.SAVE_NOTE || whichWantDo == NoteStatus.NEW_NOTE){
                                    val rnd = Random.Default
                                    val color: Int = android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                                    viewModel.onEvent(
                                        AddEditEvent.SaveOrEditNote(
                                            NoteModelDto(
                                                id = state.value.data?.id ?: 0,
                                                color = color,
                                                title = title.value,
                                                description = description.value,
                                            ),
                                            state.value.editMode
                                        )
                                    )
                                }else if(whichWantDo == NoteStatus.DELETE_NOTE){
                                    if (state.value.data != null){
                                        viewModel.onEvent(
                                            AddEditEvent.DeleteNote(state.value.data!!)
                                        )
                                    }
                                }
                                showDialog = !showDialog
                                rememberNavHostController.navigate(Routes.NOTE_PAGE.toString())
                            }
                        )
                    }
                )
            }
        }
    )

    // User want to edit note ?
    LaunchedEffect(key1 = Unit) {
        if(getNoteId != NoteStatus.NEW_NOTE.ordinal){
            viewModel.onEvent(
                AddEditEvent.GetNoteById(
                    noteId = getNoteId
                )
            )
        }
    }
}