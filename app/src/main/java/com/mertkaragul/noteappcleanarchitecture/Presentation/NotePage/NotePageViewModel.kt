package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Impl.NoteImpl
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.NotesUseCase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotePageViewModel @Inject constructor(
    private val notesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state : State<NoteState> = _state

    private fun GetNotes(){
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


    fun onEvent(event : NoteEvent){
        when(event){
            is NoteEvent.GetNotes -> {
                GetNotes()
            }
        }
    }
}