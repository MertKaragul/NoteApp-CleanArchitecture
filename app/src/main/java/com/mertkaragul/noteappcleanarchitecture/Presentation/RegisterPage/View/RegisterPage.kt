package com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.View

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.RegisterEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.RegisterState
import com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.RegisterViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.ImageCircleWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteButtonWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.TextFieldWidgetOutline

@Composable
fun RegisterPage(
    rememberNavController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state = viewModel.state
    var name by remember{ mutableStateOf("John") }
    var surname by remember {  mutableStateOf("Doe") }
    var selectImage by remember { mutableStateOf(false) }
    var imageSelectedUri by remember {  mutableStateOf<Uri>(value = Uri.EMPTY) }
    // model
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){
        if(it != null) imageSelectedUri = it
    }

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    if(!state.value.data.isNullOrEmpty()) rememberNavController.navigate("checkUser")

     Column(
         modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Text(
             text = "Welcome",
             fontSize =  35.sp,
             fontWeight = FontWeight.ExtraBold
         )

         Text(
             text  = "Let's create your account!",
             fontSize = 15.sp,
             fontWeight = FontWeight.W600
         )

         Spacer(modifier = Modifier.height(10.dp))

         ImageCircleWidget(
             width = (width * .30).dp,
             height = (height * .15).dp,
             image = imageSelectedUri,
             selectImage = {
                 selectImage = !selectImage
             }
         )

         Spacer(modifier = Modifier.height(10.dp))

         TextFieldWidgetOutline(
             name,
             textChanged = { name = it },
             placeholder = "Name",
             errorMessage = "Name is required !"
         )
         Spacer(modifier = Modifier.height(10.dp))

         TextFieldWidgetOutline(
             surname,
             textChanged = { surname = it },
             placeholder = "Surname",
             errorMessage = "Surname is required !"
         )

         Spacer(modifier = Modifier.height(10.dp))
         NoteButtonWidget(
             text = "Register",
             onClick = {
                // register user
                viewModel.onEvent(
                    RegisterEvent.RegisterUser(UserModel(name,surname,imageSelectedUri.toString()))
                )
             },
             modifier = Modifier
                 .width((width * .70).dp)
                 .height(50.dp)
         )
     }




    LaunchedEffect(key1 = selectImage){
        if (selectImage) launcher.launch(PickVisualMediaRequest())
    }
}