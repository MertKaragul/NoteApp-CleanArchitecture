package com.mertkaragul.noteappcleanarchitecture.Presentation.UserPage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase.GetUserUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUserCase
) : ViewModel() {
    private val _state = mutableStateOf<UserState>(UserState())
    val state = _state

    private fun getUser(){
        userUseCase.invoke()
            .onEach {
            when(it){
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        data = it.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = it.error ?: "Something went wrong"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(userEvent: UserEvent){
        when(userEvent){
            is UserEvent.GetUser -> {
                getUser()
            }
        }
    }

}