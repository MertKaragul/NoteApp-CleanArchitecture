package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember {  mutableStateOf("") }
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
                    AnimatedVisibility(
                        visible = searchActive,
                        modifier = Modifier.padding(10.dp)
                        ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it},
                            modifier = Modifier.width(200.dp),
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
                            }
                        )
                    }

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
                modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(state.value.isLoading){
                    CircularProgressIndicator()
                }else{
                    if (state.value.data.isNullOrEmpty() || state.value.data == null){
                        NoteNotFound()
                    }else{
                        NoteListView(noteList = state.value.data!!,notePageViewModel)
                    }
                }
            }
            it.calculateBottomPadding()
        }
    )

    LaunchedEffect(key1 = Unit) {
        notePageViewModel.onEvent(NoteEvent.GetNotes)
    }
}