package com.mertkaragul.noteappcleanarchitecture.Common

import androidx.compose.ui.graphics.Color

object Constants {
    const val DATABASE_NAME = "NoteAppDatabase"
    val NOTE_BACKGROUND_LIST : List<Color> = mutableListOf(
        Color.Gray.copy(alpha = 0.5F),
        Color.Blue.copy(alpha = 0.5F),
        Color.DarkGray.copy(alpha = 0.5F),
        Color.LightGray.copy(alpha = 0.5F),
        Color.Magenta.copy(alpha = 0.5F),
        Color.Red.copy(alpha = 0.5F),
        Color.Cyan.copy(alpha = 0.5F),
        Color.Yellow.copy(alpha = 0.5F)
    )
}