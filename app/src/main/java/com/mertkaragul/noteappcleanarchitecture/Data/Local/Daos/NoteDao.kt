package com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel

@Dao
interface NoteDao{
    @Query("SELECT * FROM NoteModel")
    suspend fun getNotes() : List<NoteModel>
    @Query("SELECT * FROM NoteModel WHERE id LIKE :id")
    suspend fun findNoteById(id : Int) : NoteModel
    @Query("SELECT * FROM NoteModel WHERE title LIKE '%' || :title || '%'")
    suspend fun findNoteByTitle(title : String) : List<NoteModel>
    @Insert
    suspend fun insert(noteModel: NoteModel)
    @Update
    suspend fun update(noteModel: NoteModel)
    @Delete
    suspend fun delete(noteModel: NoteModel)
}