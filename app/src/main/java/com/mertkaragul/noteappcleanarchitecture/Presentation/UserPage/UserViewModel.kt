package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    val userUseCase: UserUseCase
) : ViewModel() {
    private val _state = mutableStateOf<UserState>(UserState())
    val state = _state

    init {
        getUser()
    }

    private fun getUser(){
        userUseCase.getUser().onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = it.error ?: "Something went wrong"
                    )
                }

                is Resource.Success -> {
                    if(_state.value.data == null){
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "User not found"
                        )
                    }else{
                        _state.value = _state.value.copy(
                            data = it.data,
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}