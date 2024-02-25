package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

sealed class UserEvent {
    data class FindUserById(val id : Int) : UserEvent()
}