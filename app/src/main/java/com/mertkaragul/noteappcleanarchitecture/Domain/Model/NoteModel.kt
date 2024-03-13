package com.mertkaragul.noteappcleanarchitecture.Domain.Model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val shortDesc : String,
    val description : String,
    val image : String,
    val createTime : String,
    val updateTime : String,
    val color : Int
)

fun NoteModel.toNodeModelDto() : NoteModelDto{
    return NoteModelDto(id, title, shortDesc, description, image, color)
}