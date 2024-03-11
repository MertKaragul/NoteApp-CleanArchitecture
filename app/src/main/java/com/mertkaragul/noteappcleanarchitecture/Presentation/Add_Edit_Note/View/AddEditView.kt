package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View

import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.AddEditViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View.Dialogs.SaveDialog
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View.NoteListView
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View.NoteNotFound
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.ImageCircleWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteButtonWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.TextFieldWidgetOutline
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.noteFontFamily
import com.mertkaragul.noteappcleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    jsonModel : String? = null,
    rememberNavHostController: NavHostController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.editModel

    var title by remember { mutableStateOf((state.value.data?.title ?: "")) }
    var shortDescription by remember { mutableStateOf((state.value.data?.shortDesc ?: "")) }
    var description by remember { mutableStateOf((state.value.data?.description ?: "")) }
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
                    value = title,
                    onValueChange = {
                        text -> title = text
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
                    value = description,
                    onValueChange = {
                            text -> description = text
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

            SaveDialog(showVisibility = showVisibility) { status ->
                showVisibility = status
            }

            it.calculateBottomPadding()
        }
    )
}