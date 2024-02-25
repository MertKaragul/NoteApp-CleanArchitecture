package com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import java.util.Date

@Entity
data class NoteModelDto(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val shortDesc : String,
    val description : String,
    val image : String,
    val createTime : String,
    val updateTime : String
)


fun NoteModelDto.toNoteModel() : NoteModel {
    return NoteModel(this.title,this.shortDesc,this.description,this.image)
}