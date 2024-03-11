package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val iNoteRepo: INoteRepo
) {
    fun invoke(nodeModel: NoteModel) = flow<Resource<String>> {
        emit(Resource.Loading())
        iNoteRepo.insert(nodeModel)
        emit(Resource.Success("success"))
    }.catch {
    emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }

}