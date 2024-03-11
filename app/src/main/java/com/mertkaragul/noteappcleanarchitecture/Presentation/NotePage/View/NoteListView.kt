package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.MainActivity
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.ImageCircleWidget

@Composable
fun NoteListView(
    noteList : List<NoteModelDto>
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(noteList){
            try{
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(Uri.parse(it.image), flag)
            }catch (e:SecurityException){
                // nothing
            }
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(it.image)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp).fillMaxWidth(),
                    clipToBounds = true,
                    alignment = Alignment.Center,
                )
                Text(
                    it.title,
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp
                    )
                )

                Text(it.shortDesc)
            }
        }
    }
}