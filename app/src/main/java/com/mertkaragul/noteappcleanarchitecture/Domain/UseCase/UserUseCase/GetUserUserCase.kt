package com.mertkaragul.noteappcleanarchitecture.Domain.UseCase.UserUseCase

import com.mertkaragul.noteappcleanarchitecture.Common.Resource
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.toUserModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUserCase @Inject constructor(
    private val iUserRepo: IUserRepo
) {
    fun invoke() = flow<Resource<List<UserModelDto>>>{
        emit(Resource.Loading())
        println(iUserRepo.getAll())
        emit(Resource.Success(iUserRepo.getAll().map { it.toUserModelDto() }))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Something went wrong"))
    }


}