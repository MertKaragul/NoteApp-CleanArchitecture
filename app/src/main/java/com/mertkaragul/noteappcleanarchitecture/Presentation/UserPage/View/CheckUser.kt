package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.UserViewModel

@Composable
fun CheckUser(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val state = userViewModel.state
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.value.isLoading){
            CircularProgressIndicator()
        }else if(!state.value.error.isNullOrEmpty()){
            RegisterPage()
        }else{
            Text("user found route !", color = Color.Black)
        }
    }

}