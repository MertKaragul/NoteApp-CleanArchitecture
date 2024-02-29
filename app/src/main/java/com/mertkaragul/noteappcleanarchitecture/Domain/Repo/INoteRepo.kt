package com.mertkaragul.noteappcleanarchitecture.Domain.Repo

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel

interface INoteRepo {
    suspend fun getAll() : List<NoteModel>

    suspend fun findUserById(id : Int) : NoteModel

    suspend fun insert(noteModel: NoteModel)

    suspend fun update(noteModel: NoteModel)

    suspend fun delete(noteModel: NoteModel)
}