package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.NoteEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import com.mertkaragul.noteappcleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteViewAppBar(
    title : String,
    searchActive : Boolean,
    searchActiveChanged: (Boolean) -> Unit,
    searchText : String,
    searchTextChanged : (String) -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp
    return TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = noteFontFamily,
                    fontSize = 35.sp
                )
            )
        },

        actions = {
            AnimatedVisibility(
                visible = searchActive,
                modifier = Modifier.padding(10.dp),
                enter = slideInHorizontally { it - 100 },
                exit = slideOutHorizontally { it + 500 }
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = searchTextChanged,
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
                                searchActiveChanged(searchActive)
                            }
                        )
                    }
                )
            }
            AnimatedVisibility(visible = !searchActive) {
                Button(
                    onClick = {
                        searchActiveChanged(searchActive)
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
}