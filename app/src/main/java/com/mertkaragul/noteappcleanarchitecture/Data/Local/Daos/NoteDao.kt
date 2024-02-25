package com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

@Dao
interface NoteDao{
    @Query("SELECT * FROM NOTEMODELDTO")
    suspend fun getNotes() : List<NoteModelDto>
    @Query("SELECT * FROM NOTEMODELDTO WHERE id LIKE :id")
    suspend fun findNoteById(id : Int) : NoteModelDto
    @Query("SELECT * FROM NOTEMODELDTO WHERE title LIKE :title")
    suspend fun findNoteByTitle(title : String) : List<NoteModelDto>
    @Insert
    suspend fun insert(noteModelDto: NoteModelDto)
    @Update
    suspend fun update(noteModelDto: NoteModelDto)
    @Delete
    suspend fun delete(noteModelDto: NoteModelDto)
}