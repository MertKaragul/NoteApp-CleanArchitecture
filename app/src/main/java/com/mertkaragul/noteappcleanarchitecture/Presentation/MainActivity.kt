package com.mertkaragul.noteappcleanarchitecture.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mertkaragul.noteappcleanarchitecture.Common.NoteStatus
import com.mertkaragul.noteappcleanarchitecture.Common.Routes
import com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note.View.AddEditView
import com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage.View.NoteView
import com.mertkaragul.noteappcleanarchitecture.Presentation.ui.theme.NoteAppCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppCleanArchitectureTheme(
                darkTheme = true,
                dynamicColor = true
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val rememberNavController = rememberNavController()

                    NavHost(navController = rememberNavController,
                        startDestination = Routes.NOTE_PAGE.toString()){

                        composable(Routes.NOTE_PAGE.toString()){
                            NoteView(rememberNavController)
                        }

                        composable(
                            "${Routes.ADD}/{noteId}",
                            arguments = listOf(navArgument("noteId") {
                                type = NavType.IntType
                            })
                        ){
                            AddEditView(
                                getNoteId = it.arguments?.getInt("noteId") ?: NoteStatus.NEW_NOTE.ordinal,
                                rememberNavHostController = rememberNavController
                            )
                        }
                    }
                }
            }
        }
    }
}