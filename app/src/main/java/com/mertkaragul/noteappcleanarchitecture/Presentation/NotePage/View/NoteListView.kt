package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteDialogButton
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteDialogs
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListView(
    noteList : List<NoteModelDto>,
    wantDeleteNote : (NoteModelDto) -> Unit,
    noteWantEdit : (Int) -> Unit
) {
    var longPress by remember { mutableStateOf(false) }
    var clickedNote by remember { mutableStateOf<NoteModelDto?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(noteList){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .combinedClickable(
                        onClick = {
                            longPress = false
                            clickedNote = null
                            noteWantEdit.invoke(it.id)
                        },
                        onLongClick = {
                            longPress = !longPress
                            clickedNote = it
                        },
                        onLongClickLabel = it.title
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(it.color).copy(0.5F)
                ),
            ) {
                Text(
                    it.title,
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        fontSize = 25.sp,
                        fontFamily = noteFontFamily,
                    ),
                    modifier = Modifier
                        .padding(50.dp)
                )
            }
        }
    }

    AnimatedVisibility(visible = longPress) {
        NoteDialogs(
            title = "Are you sure want delete note?",
            sendBackVisibility = {longPress = it},
            buttons = {
                NoteDialogButton(
                    text = "Cancel",
                    color = Color.Red,
                    onClicked = {
                        longPress = false
                        clickedNote = null
                    }
                )
                Spacer(modifier = Modifier.padding(3.dp))
                NoteDialogButton(
                    text = "Delete",
                    color = Color(0xff30BE71),
                    onClicked = {
                        if(clickedNote != null){
                            wantDeleteNote(clickedNote!!)
                        }
                    }
                )
            }
        )
    }
}
