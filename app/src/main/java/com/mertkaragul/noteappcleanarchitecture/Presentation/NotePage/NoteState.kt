package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

data class NoteState(
    val data : List<NoteModelDto>? = null,
    val error : String? = null,
    val isLoading : Boolean = false,
    val noteWantEdit : String? = null
)