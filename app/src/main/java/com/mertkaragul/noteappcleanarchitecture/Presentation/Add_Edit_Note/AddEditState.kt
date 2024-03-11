package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

data class AddEditState(
    val error : String? = null,
    val isLoading : Boolean = false,
    val data : NoteModelDto? = null,
    val editMode : Boolean = false
)