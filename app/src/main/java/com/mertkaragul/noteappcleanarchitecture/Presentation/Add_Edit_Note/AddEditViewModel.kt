package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.DeleteNoteUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.GetNoteByIdUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.InsertNoteUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.SearchNoteUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _editModel = mutableStateOf(AddEditState())
    val editModel : State<AddEditState> = _editModel
    private fun addOrEditNode(noteModelDto: NoteModelDto, editMode : Boolean){
        if(!editMode){
            insertNoteUseCase.invoke(
                NoteModel(
                    noteModelDto.id,
                    noteModelDto.title,
                    noteModelDto.shortDesc,
                    noteModelDto.description,
                    noteModelDto.image,
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString(),
                    noteModelDto.color
                )
            ).onEach {
                println(it)
            }.launchIn(viewModelScope)
        }else{
            updateNoteUseCase.invoke(
                NoteModel(
                    noteModelDto.id,
                    noteModelDto.title,
                    noteModelDto.shortDesc,
                    noteModelDto.description,
                    noteModelDto.image,
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString(),
                    noteModelDto.color
                )
            ).onEach {
                println(it)
            }.launchIn(viewModelScope)
        }
    }

    private fun getNoteById(noteId : Int){
        getNoteByIdUseCase.invoke(noteId)
            .onEach {
                when(it){
                    is Resource.Loading -> {
                        _editModel.value = _editModel.value.copy(
                            isLoading = true,
                            editMode = false
                        )
                    }

                    is Resource.Error -> {
                        _editModel.value = _editModel.value.copy(
                            isLoading = false,
                            error = it.error,
                            editMode = false
                        )
                    }
                    is Resource.Success -> {
                        _editModel.value = _editModel.value.copy(
                            isLoading = false,
                            data = it.data,
                            editMode = true
                        )
                    }

                }
            }.launchIn(viewModelScope)
    }

    private fun deleteNote(noteModelDto: NoteModelDto){
        deleteNoteUseCase.invoke(noteModelDto)
            .onEach {
                println(it)
            }.launchIn(viewModelScope)
    }


    fun onEvent(addEditEvent: AddEditEvent){
        when(addEditEvent){
            is AddEditEvent.GetNoteById -> {
                getNoteById(addEditEvent.noteId)
            }

            is AddEditEvent.SaveOrEditNote -> {
                addOrEditNode(addEditEvent.noteModelDto, addEditEvent.isEdit )
            }

            is AddEditEvent.DeleteNote -> {
                deleteNote(addEditEvent.noteModelDto)
            }
        }
    }

}