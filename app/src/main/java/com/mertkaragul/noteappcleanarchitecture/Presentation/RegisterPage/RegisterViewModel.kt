package com.mertkaragul.noteappcleanarchitecture.Presentation.RegisterPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUserUseCase: UserUseCase
) : ViewModel() {
    private val _state = mutableStateOf<RegisterState>(RegisterState())
    val state : State<RegisterState> = _state

    private fun registerUser(userModel: UserModel){
        userUserUseCase.insertUser(userModel)
            .onEach {
                when(it){
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            data = "",
                            error = it.error ?: "Something went wrong",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            data = "",
                            error = "",
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            data = it.data,
                            error = "",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun onEvent(registerEvent: RegisterEvent){
        when(registerEvent){
            is RegisterEvent.RegisterUser -> {
                registerUser(registerEvent.userModel)
            }
        }
    }



}