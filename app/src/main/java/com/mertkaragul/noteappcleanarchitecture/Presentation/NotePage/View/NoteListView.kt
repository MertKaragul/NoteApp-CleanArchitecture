package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Common.Constants.NOTE_BACKGROUND_LIST
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.NotePageViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListView(
    noteList : List<NoteModelDto>,
    viewModel : NotePageViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(noteList){
            var longPress by remember { mutableStateOf(false) }
            val rnd = Random.Default
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .combinedClickable(
                        onClick = {
                            longPress = false
                        },
                        onLongClick = {
                            longPress = !longPress
                        },
                        onLongClickLabel = it.title
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color(color).copy(alpha = 0.5F)
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
}