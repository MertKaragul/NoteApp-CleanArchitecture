package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.ImageCircleWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.NoteButtonWidget
import com.mertkaragul.noteappcleanarchitecture.Presentation.Widgets.TextFieldWidgetOutline

@Composable
fun RegisterPage(

) {
    var name by remember{ mutableStateOf("John") }
    var surname by remember {  mutableStateOf("Doe") }
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

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
             image = 0,
             selectImage = {
                 println("Hello !")
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
             },
             modifier = Modifier
                 .width((width * .70).dp)
                 .height(50.dp)
         )
     }


}