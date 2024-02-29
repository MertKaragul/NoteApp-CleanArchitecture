package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.NotePageViewModel

@Composable
fun NoteView(
    notePageViewModel: NotePageViewModel = hiltViewModel()
) {
    val state = notePageViewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.value.isLoading){
            CircularProgressIndicator()
        }else{
            if (state.value.data.isNullOrEmpty() || state.value.data == null){
                Text(
                    text = "Note not found"
                )
            }else{
                NoteListView(noteList = state.value.data!!)
            }
        }
    }
}