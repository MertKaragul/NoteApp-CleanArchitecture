package com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO

import androidx.compose.ui.graphics.Color
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
    val color : Int
)

fun NoteModelDto.toNoteModel() : NoteModel{
    return NoteModel(this.id,this.title,this.shortDesc,this.description,this.description,"","",this.color)
}


