package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.InsertNoteUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val gson : Gson,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {
    private val _editModel = mutableStateOf(AddEditState())
    val editModel : State<AddEditState> = _editModel


    private fun parseJson(jsonString : String){
        val parseJson = gson.fromJson<NoteModelDto>(jsonString,object : TypeToken<NoteModelDto>() {}.type)
        if(parseJson != null){
            _editModel.value = _editModel.value.copy(
                data = NoteModelDto(
                    parseJson.id,
                    parseJson.title,
                    parseJson.shortDesc,
                    parseJson.description,
                    parseJson.image,
                    parseJson.color
                ),
                isLoading = false,
                error = "",
                editMode = true
            )
        }
    }

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



    fun onEvent(addEditEvent: AddEditEvent){
        when(addEditEvent){
            is AddEditEvent.CheckEdit -> {
                parseJson(addEditEvent.jsonString)
            }

            is AddEditEvent.SaveOrEditNote -> {
                addOrEditNode(addEditEvent.noteModelDto, addEditEvent.isEdit )
            }
        }
    }

}