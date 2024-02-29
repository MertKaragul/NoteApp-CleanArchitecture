package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

sealed class UserEvent {
    data object GetUser : UserEvent()
}