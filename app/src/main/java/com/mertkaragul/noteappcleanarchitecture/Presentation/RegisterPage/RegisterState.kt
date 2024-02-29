package com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage

data class RegisterState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val data : String? = null
)