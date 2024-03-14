package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mertkaragul.noteappcleanarchitecture.Common.Routes
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.NoteEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.NotePageViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import com.mertkaragul.noteappcleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(
    rememberNavController: NavHostController,
    notePageViewModel: NotePageViewModel = hiltViewModel()
) {
    val state = notePageViewModel.state
    val width = LocalConfiguration.current.screenWidthDp
    var searchActive by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes",
                        style = TextStyle(
                            fontFamily = noteFontFamily,
                            fontSize = 35.sp
                        )
                    )
                },

                actions = {
                    var searchText by remember {  mutableStateOf("") }
                    AnimatedVisibility(
                        visible = searchActive,
                        modifier = Modifier.padding(10.dp),
                        enter = slideInHorizontally { it - 100 },
                        exit = slideOutHorizontally { it + 500 }
                        ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                notePageViewModel.onEvent(
                                    NoteEvent.SearchNote(it)
                                )
                            },
                            modifier = Modifier.width((width * .5).dp),
                            singleLine = true,
                            placeholder = {
                                Text(
                                    "Search note"
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_search_24),
                                    contentDescription = ""
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_close_24),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        searchActive = !searchActive
                                    }
                                )
                            }
                        )
                    }
                    AnimatedVisibility(visible = !searchActive) {
                        Button(
                            onClick = {
                                searchActive = !searchActive
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    rememberNavController.navigate(Routes.ADD.toString())
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24) ,
                    contentDescription = ""
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(state.value.isLoading){
                    CircularProgressIndicator()
                }else{
                    if (state.value.data.isNullOrEmpty() || state.value.data == null){
                        NoteNotFound()
                    }else{
                        NoteListView(
                            noteList = state.value.data!!,
                            wantDeleteNote = { dto ->
                                notePageViewModel.onEvent(
                                    NoteEvent.DeleteNote(dto)
                                )
                            },
                            noteWantEdit = {noteId ->
                                rememberNavController.navigate("${Routes.ADD}/$noteId")
                            }
                        )
                    }
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        notePageViewModel.onEvent(NoteEvent.GetNotes)
    }
}