package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel


data class UserState(
    val data : UserModel? = null,
    val error : String? = "",
    val isLoading : Boolean = false,
)