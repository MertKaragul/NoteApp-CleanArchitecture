package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel


data class UserState(
    val data : List<UserModelDto>? = null,
    val error : String? = "",
    val isLoading : Boolean = false,
)