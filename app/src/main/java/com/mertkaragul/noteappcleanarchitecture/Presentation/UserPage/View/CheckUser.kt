package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.View.RegisterPage
import com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.UserEvent
import com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.UserViewModel

@Composable
fun CheckUser(
    rememberNavController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 =  Unit){
        userViewModel.onEvent(UserEvent.GetUser)
    }

    val state = userViewModel.state
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.value.isLoading){
            CircularProgressIndicator()
        }else if(!state.value.data.isNullOrEmpty()){
            rememberNavController.navigate("notesView")
        }else if(state.value.data.isNullOrEmpty()){
            Text("I think cannot have a account")
            Button(onClick = { rememberNavController.navigate("registerUser") }) {
                Text(text = "Let's create account!")
            }
        }else{
            Text(state.value.error.toString())
        }
    }



}