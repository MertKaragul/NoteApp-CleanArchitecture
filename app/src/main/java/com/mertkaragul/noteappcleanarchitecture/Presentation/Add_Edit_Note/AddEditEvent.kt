package com.mertkaragul.noteappcleanarchitecture.Presentation.Add_Edit_Note

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

sealed class AddEditEvent{
    data class CheckEdit(val jsonString : String) : AddEditEvent()
    data class SaveOrEditNote(val noteModelDto: NoteModelDto, val saveOrEdit : Boolean) : AddEditEvent()
}