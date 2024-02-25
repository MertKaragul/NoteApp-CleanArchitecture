package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    val iUserRepo: IUserRepo
) {
    fun getUser() = flow<Resource<UserModel>>{
        emit(Resource.Loading())
        emit(Resource.Success(iUserRepo.getAll()))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }



}