package com.mertkaragul.noteappcleanarchitecture.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage.View.RegisterPage
import com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage.View.CheckUser
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.NoteAppCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppCleanArchitectureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val rememberNavController = rememberNavController()

                    NavHost(navController = rememberNavController, startDestination = "checkUser"){
                        composable("checkUser"){
                            CheckUser(rememberNavController)
                        }

                        composable("registerUser"){
                            RegisterPage(rememberNavController)
                        }
                    }
                }
            }
        }
    }
}