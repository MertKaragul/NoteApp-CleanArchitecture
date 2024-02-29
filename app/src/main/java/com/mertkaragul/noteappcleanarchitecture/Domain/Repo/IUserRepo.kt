package com.mertkaragul.noteappcleanarchitecture.Domain.Repo

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel


interface IUserRepo {

    suspend fun getAll() : List<UserModel>

    suspend fun findUserById(id : Int) : UserModel

    suspend fun insert(userModel: UserModel)

    suspend fun update(userModelDto: UserModel)

    suspend fun delete(userModelDto: UserModel)
}