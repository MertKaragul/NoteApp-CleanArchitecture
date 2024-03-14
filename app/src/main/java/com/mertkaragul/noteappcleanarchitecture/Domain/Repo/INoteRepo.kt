package com.mertkaragul.noteappcleanarchitecture.Domain.Repo

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel

interface INoteRepo {
    suspend fun getAll() : List<NoteModel>

    suspend fun findNoteById(id : Int) : NoteModel
    suspend fun findNoteByTitle(searchString : String) : List<NoteModel>
    suspend fun insert(noteModel: NoteModel)

    suspend fun update(noteModel: NoteModel)

    suspend fun delete(noteModel: NoteModel)
}