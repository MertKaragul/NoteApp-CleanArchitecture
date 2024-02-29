package com.mertkaragul.noteappcleanarchitecture.Data.Impl

import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.NoteDao
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import javax.inject.Inject

class NoteImpl @Inject constructor(
    private val noteDao: NoteDao
) : INoteRepo{
    override suspend fun getAll(): List<NoteModel> {
        return noteDao.getNotes()
    }

    override suspend fun findUserById(id: Int): NoteModel {
        return noteDao.findNoteById(id)
    }

    override suspend fun insert(noteModel: NoteModel) {
        noteDao.insert(noteModel)
    }

    override suspend fun update(noteModel: NoteModel) {
        noteDao.update(noteModel)
    }

    override suspend fun delete(noteModel: NoteModel) {
        noteDao.delete(noteModel)
    }


}