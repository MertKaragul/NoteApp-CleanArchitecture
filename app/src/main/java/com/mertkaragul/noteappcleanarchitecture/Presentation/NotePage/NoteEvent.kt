package com.mertkaragul.noteappcleanarchitecture.Presentation.NotePage

sealed class NoteEvent {
    data object GetNotes : NoteEvent()
}