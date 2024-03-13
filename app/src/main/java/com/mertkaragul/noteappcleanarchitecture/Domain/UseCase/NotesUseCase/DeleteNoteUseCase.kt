package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Impl.NoteImpl
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteImpl: INoteRepo
){
    fun invoke(noteModelDto: NoteModelDto) = flow<Resource<String>>{
        noteImpl.delete(
            NoteModel(
                noteModelDto.id,
                noteModelDto.title,
                noteModelDto.shortDesc,
                noteModelDto.description,
                "",
                "",
                "",
                noteModelDto.color
            )
        )
        emit(Resource.Success(""))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }
}