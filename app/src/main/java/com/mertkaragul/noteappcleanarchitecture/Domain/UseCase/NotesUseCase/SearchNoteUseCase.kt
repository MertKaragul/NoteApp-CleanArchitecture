package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.toNodeModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNoteUseCase @Inject constructor(
    private val iNoteRepo: INoteRepo
) {
    fun invoke(searchString : String) = flow<Resource<List<NoteModelDto>>>{
        emit(Resource.Loading())
        emit(Resource.Success(iNoteRepo.findNoteByTitle(searchString.lowercase().trim()).map { it.toNodeModelDto() }))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Note not found"))
    }
}