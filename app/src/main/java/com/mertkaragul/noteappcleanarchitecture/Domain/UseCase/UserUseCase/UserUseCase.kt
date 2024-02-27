package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.internal.wait
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val iUserRepo: IUserRepo
) {
    fun getUser() = flow<Resource<UserModel>>{
        emit(Resource.Loading())
        emit(Resource.Success(iUserRepo.getAll()))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }

    fun insertUser(userModelDto: UserModel) = flow<Resource<String>>{
        emit(Resource.Loading())
        iUserRepo.insert(userModelDto)
        emit(Resource.Success("Successfully register"))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }



}