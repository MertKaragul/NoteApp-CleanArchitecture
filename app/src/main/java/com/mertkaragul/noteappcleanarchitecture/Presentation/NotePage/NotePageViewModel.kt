package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Impl.NoteImpl
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.DeleteNoteUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.GetNotesUseCase
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.SearchNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.PrimitiveIterator
import javax.inject.Inject

@HiltViewModel
class NotePageViewModel @Inject constructor(
    private val notesUseCase: GetNotesUseCase,
    private val delNoteUseCase : DeleteNoteUseCase,
    private val searchNoteUseCase: SearchNoteUseCase,
    private val gson : Gson
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state : State<NoteState> = _state

    private fun getNotes(){
        notesUseCase.invoke()
            .onEach {
                when(it){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            data = it.data,
                            error = ""
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = it.error
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }

    private fun deleteNote(noteModelDto: NoteModelDto){
        delNoteUseCase.invoke(noteModelDto).onEach {
            when(it){
                is Resource.Success -> {
                    getNotes()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun searchNote(searchString: String){
        searchNoteUseCase.invoke(searchString)
            .onEach {
                when(it){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            data = it.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = it.error
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun noteModelToJson(noteModelDto: NoteModelDto){
        _state.value = _state.value.copy(
            noteWantEdit = gson.toJson(noteModelDto)
        )
    }


    fun onEvent(event : NoteEvent){
        when(event){
            is NoteEvent.GetNotes -> {
                getNotes()
            }

            is NoteEvent.DeleteNote -> {
                deleteNote(event.noteModelDto)
            }

            is NoteEvent.SearchNote -> {
                searchNote(event.searchString)
            }

            is NoteEvent.NoteWantEditEvent -> {
                noteModelToJson(event.nodeModelDto)
            }
        }
    }
}