package com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import java.util.Date

data class NoteModelDto(
    val id : Int = 0,
    val title : String,
    val shortDesc : String,
    val description : String,
    val image : String,
)


