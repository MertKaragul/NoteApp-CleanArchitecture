package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto

sealed class NoteEvent {
    data object GetNotes : NoteEvent()
    data class DeleteNote(val noteModelDto: NoteModelDto) : NoteEvent()
    data class SearchNote(val searchString: String) : NoteEvent()
    data class NoteWantEditEvent(val nodeModelDto: NoteModelDto) : NoteEvent()
}