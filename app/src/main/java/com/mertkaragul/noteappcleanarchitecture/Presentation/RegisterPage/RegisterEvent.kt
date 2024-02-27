package com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel

sealed class RegisterEvent {
    data class RegisterUser(val userModel: UserModel) : RegisterEvent()
}