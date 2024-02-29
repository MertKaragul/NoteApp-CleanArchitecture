package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

@Composable
fun NoteListView(
    noteList : List<NoteModelDto>
) {
    LazyColumn{
        items(noteList){
            Text(it.title)
        }
    }
}